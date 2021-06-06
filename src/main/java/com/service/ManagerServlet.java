package com.service;

import com.entity.User;
import com.dao.UserDao;
import com.entity.Oplog;
import com.dao.LogDao;
import com.dao.PerDao;
import com.dao.CartDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "ManagerServlet")
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action.equals("sellers"))
        {
            UserDao userDao = new UserDao();
            List<User> sellers = new ArrayList<>();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            sellers = userDao.GetSellers();
            request.setAttribute("sellers", sellers);
            request.getRequestDispatcher("manager.jsp?username="+provider).forward(request,response);
        }
        if(action.equals("addseller"))
        {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User user = new User();
            UserDao userDao = new UserDao();
            user=userDao.exist(name,"seller");
            if(user!=null)
            {
                request.getRequestDispatcher("addseller.jsp?exist=true").forward(request,response);
            }
            else
            {
                PerDao perDao = new PerDao();
                perDao.add(name);
                user = new User();
                user.Setname(name);
                user.Setpassword(password);
                user.Setidentity("seller");
                user.Setemail(email);
                userDao.addUser(user);
                List<User> sellers = new ArrayList<>();
                sellers = userDao.GetSellers();
                request.setAttribute("sellers", sellers);
                HttpSession session=request.getSession();
                String username=(String)session.getAttribute("user");
                Date now=new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=dateFormat.format(now);
                String ip = (String)session.getAttribute("ip");
                Oplog record = new Oplog(username, "管理员", time, "添加销售员", ip);
                LogDao logDao = new LogDao();
                logDao.addoplog(record);
                request.getRequestDispatcher("manager.jsp?username="+username+"&add=true").forward(request,response);
            }
        }
        if(action.equals("delSeller"))
        {
            String name = request.getParameter("name");
            UserDao userDao = new UserDao();
            userDao.delseller(name);
            CartDao cartDao = new CartDao();
            cartDao.deleteprovider(name);
            List<User> sellers = new ArrayList<>();
            sellers = userDao.GetSellers();
            request.setAttribute("sellers", sellers);
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=dateFormat.format(now);
            String ip = (String)session.getAttribute("ip");
            Oplog record = new Oplog(username, "管理员", time, "删除销售员", ip);
            LogDao logDao = new LogDao();
            logDao.addoplog(record);
            request.getRequestDispatcher("manager.jsp?username="+username+"&del=true").forward(request,response);
        }
        if(action.equals("resetpw"))
        {
            String name=request.getParameter("name");
            String password = request.getParameter("npassword");
            String password2 = request.getParameter("cpassword");
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            if(password.equals(password2))
            {
                UserDao userDao=new UserDao();
                userDao.resetpw(name, password);
                List<User> sellers = new ArrayList<>();
                sellers = userDao.GetSellers();
                request.setAttribute("sellers", sellers);
                Date now=new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=dateFormat.format(now);
                String ip = (String)session.getAttribute("ip");
                Oplog record = new Oplog(username, "管理员", time, "重置销售员密码", ip);
                LogDao logDao = new LogDao();
                logDao.addoplog(record);
                request.getRequestDispatcher("manager.jsp?username="+username+"&update=true").forward(request,response);
            }
            else
            {
                request.getRequestDispatcher("resetpw.jsp?name="+name+"&error=true").forward(request,response);
            }
        }
    }
}
