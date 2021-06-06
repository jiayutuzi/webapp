package com.service;
import com.entity.Goods;
import com.dao.GoodsDao;
import com.entity.Cart;
import com.dao.CartDao;
import com.entity.Log;
import com.dao.LogDao;
import com.entity.User;
import com.dao.UserDao;
import com.dao.PerDao;
import com.dao.Sendemail;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.text.DecimalFormat;  
import java.util.List;
import java.util.ArrayList;
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action=request.getParameter("action");
        if(action.equals("add"))
        {
            String id = request.getParameter("id");
            String price = request.getParameter("price");
            String num = request.getParameter("number");
            int number = Integer.parseInt(num);
            String provider = request.getParameter("provider");
            String name = request.getParameter("name");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            Cart cart = new Cart();
            cart.Setid(id);
            cart.Setname(name);
            cart.Setprice(price);
            cart.Setprovider(provider);
            cart.Setuser(user);
            cart.Setnumber(number);
            CartDao cartdao = new CartDao();
            Cart exist = cartdao.exist(cart);
            if(exist!=null)
            {
                GoodsDao goodsDao = new GoodsDao();
                goodsDao.update(id, number, "sub");
                cart.Setnumber(cart.Getnumber()+exist.Getnumber());
                double price1=Double.parseDouble(exist.Getprice());
                double price2=Double.parseDouble(cart.Getprice())*cart.Getnumber();
                double sum=price1+price2;
                DecimalFormat df= new DecimalFormat("######0.00"); 
                String sumprice=df.format(sum);
                cart.Setprice(sumprice);
                cartdao.update(cart);
            }
            else{
                GoodsDao goodsDao = new GoodsDao();
                goodsDao.update(id, number, "sub");
                double price1=Double.parseDouble(cart.Getprice())*cart.Getnumber();
                DecimalFormat df= new DecimalFormat("######0.00"); 
                String sumprice=df.format(price1);
                cart.Setprice(sumprice);
                cartdao.add(cart);
            }
            request.getRequestDispatcher("ShowServlet?action=purchase&id="+id+"&op=add").forward(request,response);
        }
        if(action.equals("del")){
            CartDao cartDao = new CartDao();
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            cartDao.delete(id, user);
            GoodsDao goodsDao = new GoodsDao();
            Cart cart=cartDao.Getcart(id);
            goodsDao.update(id, cart.Getnumber(), "add");
            request.getRequestDispatcher("ShowServlet?action=showcart").forward(request,response);
        }
        if(action.equals("empty")){
            CartDao cartDao = new CartDao();
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            cartDao.delete(user);
            GoodsDao goodsDao = new GoodsDao();
            List<Cart> carts = cartDao.showcart(user);
            for(Cart c:carts)
                goodsDao.update(c.Getid(), c.Getnumber(), "add");
            request.getRequestDispatcher("ShowServlet?action=showcart").forward(request,response);
        }
        if (action.equals("purchase")) {
            CartDao cartDao = new CartDao();
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartDao.showcart(user);
            LogDao logDao=new LogDao();
            if (cart_list != null)
            {
                Date now=new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=dateFormat.format(now);
                for (Cart cart : cart_list)
                {   
                    GoodsDao goodsDao = new GoodsDao();
                    Goods goods=goodsDao.Getgoods(cart.Getid());
                    Log log=new Log();
                    log.Setid(cart.Getid());
                    log.Setname(goods.Getname());
                    log.Setnumber(cart.Getnumber());
                    log.Setop("购买");
                    log.Setprovider(cart.Getprovider());
                    log.Setprice(goods.Getprice());
                    log.Setuser(user);
                    log.Settime(time);
                    log.Setclass(goods.Getclass());
                    logDao.add(log);
                    int sell = cart.Getnumber() + goods.Getsell();
                    goodsDao.update(goods.Getid(),sell);
                    PerDao perDao = new PerDao();
                    perDao.update(cart);
                }
            }
            cartDao.delete(user);
            UserDao userDao = new UserDao();
            User u = userDao.exist(user,"consumer");
            Sendemail send=new Sendemail();
            try{
                send.sendmail(u.Getemail());
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            request.getRequestDispatcher("ShowServlet?action=showcart&op=purchase").forward(request,response);

        }
    }
}
