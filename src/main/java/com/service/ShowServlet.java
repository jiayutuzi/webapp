package com.service;

import com.entity.Goods;
import com.dao.GoodsDao;
import com.entity.Cart;
import com.dao.CartDao;
import com.entity.Log;
import com.entity.Loginlog;
import com.entity.Oplog;
import com.dao.LogDao;
import com.entity.Performance;
import com.dao.PerDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;  
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "ShowServlet")
public class ShowServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action=request.getParameter("action");
        if(action.equals("manage_goods"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            goods_list=goodsDao.showmygoods(username);
            request.setAttribute("goods", goods_list);
            String op = request.getParameter("op");
            request.setAttribute("username", username);
            if(op!=null&&op.equals("delete"))
                request.getRequestDispatcher("goods_manage.jsp?username="+username+"&op=delete").forward(request,response);
            else if(op!=null&&op.equals("update"))
                request.getRequestDispatcher("goods_manage.jsp?username="+username+"&op=update").forward(request,response);
            else 
                request.getRequestDispatcher("goods_manage.jsp?username="+username).forward(request,response);
        }
        if(action.equals("manage_goods_class"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            String Class=request.getParameter("class");
            goods_list=goodsDao.showmygoods(username,Class);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("goods_manage.jsp?username="+username).forward(request,response);
        }
        if(action.equals("showgoods"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            if(username==null)
                username="未登录";
            goods_list=goodsDao.showgoods();
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("shop.jsp?username="+username).forward(request,response);
        }
        if(action.equals("showgoods_class"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            if(username==null)
                username="未登录";
            String Class=request.getParameter("class");
            goods_list=goodsDao.showgoods(Class);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("shop.jsp?username="+username).forward(request,response);
        }
        if(action.equals("purchase"))
        {
            GoodsDao goodsDao = new GoodsDao();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            if(username==null)
                username="未登录";
            Goods goods=new Goods();
            String id=request.getParameter("id");
            goods=goodsDao.Getgoods(id);
            List<Goods> recommand_list = new ArrayList<>();
            recommand_list=goodsDao.recommand(username,id);
            request.setAttribute("information", goods);
            request.setAttribute("username", username);
            request.setAttribute("recommand", recommand_list);
            request.getRequestDispatcher("purchase.jsp?username="+username).forward(request,response);
        }
        if(action.equals("showcart"))
        {
            CartDao cartdao = new CartDao();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");                
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartdao.showcart(username);
            request.setAttribute("cart", cart_list);
            String sum_price = cartdao.Getprice(username);
            String op=request.getParameter("op");
            request.setAttribute("username", username);
            request.setAttribute("price", sum_price);
            if(op!=null && op.equals("purchase"))
                request.getRequestDispatcher("cart.jsp?username=" + username + "&price=" + sum_price+"&op=purchase").forward(request,response);
            else
                request.getRequestDispatcher("cart.jsp?username=" + username + "&price=" + sum_price).forward(request,response);
        }
        if(action.equals("showbrowlog"))
        {
            LogDao logDao = new LogDao();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");  
            List<Log> log_list = new ArrayList<>();
            log_list = logDao.showbrowlog(provider);
            request.setAttribute("log", log_list);
            request.setAttribute("username", provider);
            request.getRequestDispatcher("slog.jsp?op=browse&username=" + provider).forward(request,response);
        }
        if(action.equals("showbuylog"))
        {
            LogDao logDao = new LogDao();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");  
            List<Log> log_list = new ArrayList<>();
            log_list = logDao.showbuylog(provider);
            request.setAttribute("log", log_list);
            request.setAttribute("username", provider);
            request.getRequestDispatcher("slog.jsp?op=buy&username=" + provider).forward(request,response);
        }
        if(action.equals("status"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            goods_list=goodsDao.showmygoods(username);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            double profit=0;
            for(int i=0;i<goods_list.size();i++)
            {
                double price=Double.parseDouble(goods_list.get(i).Getprice());
                profit+=price*goods_list.get(i).Getsell();
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            request.setAttribute("price", sum_price);
            request.getRequestDispatcher("sgoodsstatus.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("status_class"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            String Class=request.getParameter("class");
            goods_list=goodsDao.showmygoods(username,Class);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            double profit=0;
            for(int i=0;i<goods_list.size();i++)
            {
                double price=Double.parseDouble(goods_list.get(i).Getprice());
                profit+=price*goods_list.get(i).Getsell();
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            request.setAttribute("price", sum_price);
            request.getRequestDispatcher("sgoodsstatus.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("allstatus"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            goods_list=goodsDao.showgoods();
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            double profit=0;
            for(int i=0;i<goods_list.size();i++)
            {
                double price=Double.parseDouble(goods_list.get(i).Getprice());
                profit+=price*goods_list.get(i).Getsell();
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            request.setAttribute("price", sum_price);
            request.getRequestDispatcher("mgoodsstatus.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("allstatus_class"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            String Class=request.getParameter("class");
            goods_list=goodsDao.showgoods(Class);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            double profit=0;
            for(int i=0;i<goods_list.size();i++)
            {
                double price=Double.parseDouble(goods_list.get(i).Getprice());
                profit+=price*goods_list.get(i).Getsell();
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            request.setAttribute("price", sum_price);
            request.getRequestDispatcher("mgoodsstatus.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("showperformance"))
        {
            PerDao perDao = new PerDao();
            List<Performance> per_list=new ArrayList<>();
            per_list=perDao.showpPerformance();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            double sport =0;
            double life=0;
            double food =0;
            double electronic=0;
            double cloth=0;
            double all=0;
            for(int i=0;i<per_list.size();i++)
            {
                sport+=Double.parseDouble(per_list.get(i).Getsport());
                life+=Double.parseDouble(per_list.get(i).Getlife());
                food+=Double.parseDouble(per_list.get(i).Getfood());
                electronic+=Double.parseDouble(per_list.get(i).Getelectronic());
                cloth+=Double.parseDouble(per_list.get(i).Getcloth());
                all+=Double.parseDouble(per_list.get(i).Getall());
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sport_str = df.format(sport);
            String life_str = df.format(life);
            String food_str=df.format(food);
            String electronic_str=df.format(electronic);
            String cloth_str=df.format(cloth);
            String all_str=df.format(all);
            Performance p = new Performance("总销售额",life_str,sport_str,food_str,electronic_str,cloth_str,all_str);
            per_list.add(p);
            request.setAttribute("performance", per_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("performance.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("search"))
        {
            String provider = (String)request.getParameter("search");
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            goods_list=goodsDao.showmygoods(provider);
            request.setAttribute("goods", goods_list);
            request.setAttribute("username", username);
            double profit=0;
            for(int i=0;i<goods_list.size();i++)
            {
                double price=Double.parseDouble(goods_list.get(i).Getprice());
                profit+=price*goods_list.get(i).Getsell();
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            request.setAttribute("price", sum_price);
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=dateFormat.format(now);
            String ip = (String)session.getAttribute("ip");
            Oplog record = new Oplog(username, "管理员", time, "查询销售员负责商品", ip);
            LogDao logDao = new LogDao();
            logDao.addoplog(record);
            request.getRequestDispatcher("mgoodsstatus.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("loginlog"))
        {
            LogDao logDao = new LogDao();
            List<Loginlog> log_list=new ArrayList<>(); 
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.showlog();
            request.setAttribute("loginlog", log_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("loginlog.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("buylog"))
        {
            LogDao logDao = new LogDao();
            List<Log> log_list=new ArrayList<>(); 
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.buylog(username);
            request.setAttribute("buylog", log_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("buylog.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("browlog"))
        {
            LogDao logDao = new LogDao();
            List<Log> log_list=new ArrayList<>(); 
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.browlog(username);
            request.setAttribute("browlog", log_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("browlog.jsp?username=" + username ).forward(request,response);
        }
        if(action.equals("showoplog"))
        {
            LogDao logDao = new LogDao();
            List<Oplog> log_list=new ArrayList<>(); 
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.showoplog();
            request.setAttribute("oplog", log_list);
            request.setAttribute("username", username);
            request.getRequestDispatcher("oplog.jsp?username=" + username ).forward(request,response);
        }
    }
}
