package com.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.io.IOException;
import com.entity.Goods;
import com.dao.GoodsDao;
import com.entity.Log;
import com.dao.LogDao;
@WebServlet(name = "AjaxServlet")
public class AjaxServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Date now=new Date();
        HttpSession hs = request.getSession();
        long starttime=(long)hs.getAttribute("starttime");
        String id=(String)hs.getAttribute("logid");
        long endtime = now.getTime();
        int time=(int)((endtime - starttime)/1000);
        System.out.println(time);
        String goodsid = (String)request.getParameter("goodsid");
        GoodsDao goodsDao = new GoodsDao();
        Goods g = goodsDao.Getgoods(goodsid);
        String user=(String)hs.getAttribute("user");
        Log log =new Log();
        log.Setid(g.Getid());
        log.Setname(g.Getname());
        log.Setnumber(time);
        log.Setop("浏览");
        log.Setprovider(g.Getprovider());
        log.Setprice(g.Getprice());
        log.Setuser(user);
        log.Settime(id);
        log.Setclass(g.Getclass());
        LogDao logDao = new LogDao();
        logDao.add(log);
    }
}
