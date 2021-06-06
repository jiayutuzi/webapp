package com.service;

import com.entity.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "RegisterLoginServlet")
public class RegisterLoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action.equals("register"))
        {
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User user = new User();
            UserDao userDao = new UserDao();
            user=userDao.exist(username,"consumer");
            if(user!=null)
            {
                request.getRequestDispatcher("register.jsp?register=no").forward(request,response);
            }
            else
            {
                user = new User();
                user.Setname(username);
                user.Setname(username);
                user.Setpassword(password);
                user.Setidentity("consumer");
                user.Setemail(email);
                userDao.addUser(user);
                request.getRequestDispatcher("Login.jsp?register=yes").forward(request,response);
            }
        }
        if(action.equals("login"))
        {
            HttpSession hs = request.getSession();
            hs.invalidate();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String identity = request.getParameter("identity");
            String ip = null;
            String ipAddresses = request.getHeader("X-Forwarded-For");
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("X-Real-IP");
            }
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ip = ipAddresses.split(",")[0];
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ip = request.getRemoteAddr();
            }
            UserDao userDao = new UserDao();
            User user = userDao.login(username,password,identity);
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String logintime=dateFormat.format(now);
            if(user != null){
                if(user.Getidentity().equals("root"))
                {
                    hs = request.getSession();
                    hs.setAttribute("user", "管理员");
                    hs.setAttribute("identity", "管理员");
                    hs.setAttribute("logintime", logintime);
                    hs.setAttribute("ip", ip);
                    request.getRequestDispatcher("ManagerServlet?action=sellers").forward(request,response);
                    //request.getRequestDispatcher("ShowServlet").forward(request,response);
                }
                else if(user.Getidentity().equals("seller"))
                {
                    hs = request.getSession();
                    hs.setAttribute("user", username);
                    hs.setAttribute("identity", "销售员");
                    hs.setAttribute("logintime", logintime);
                    hs.setAttribute("ip", ip);
                    request.getRequestDispatcher("ShowServlet?action=manage_goods").forward(request,response);
                }
                else
                {
                    hs = request.getSession();
                    hs.setAttribute("user", username);
                    hs.setAttribute("identity", "普通用户");
                    hs.setAttribute("logintime", logintime);
                    hs.setAttribute("ip", ip);
                    request.getRequestDispatcher("ShowServlet?action=showgoods").forward(request,response);
                }
            }
            else {
                response.sendRedirect("Login.jsp?error=yes");
            }
        }
        if(action.equals("logout"))
        {
            HttpSession hs = request.getSession();
            hs.invalidate();
            response.sendRedirect("ShowServlet?action=showgoods");
        }
    }
}