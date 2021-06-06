package com.service;

import com.entity.Goods;
import com.dao.GoodsDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.entity.Oplog;
import com.dao.LogDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;  
import java.io.*;  
import java.util.List;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  

@WebServlet(name = "GoodsServlet")
public class GoodsServlet extends HttpServlet {
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
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            String path=request.getSession().getServletContext().getRealPath("/img"); 
            factory.setRepository(new File(path));  
            factory.setSizeThreshold(1024*1024) ;  
            ServletFileUpload upload = new ServletFileUpload(factory); 
            Goods goods=new Goods();
            GoodsDao goodsDao=new GoodsDao();
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String id=dateFormat.format(now);
            goods.Setid(id);
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            goods.Setprovider(provider);
            goods.Setsell(0);
             try{
                List<FileItem> list = (List<FileItem>)upload.parseRequest(request); 
                for(FileItem item : list)  
                {  
                    if(item.isFormField())  
                    {
                        String name = item.getFieldName();                       
                        String value = new String(item.getString().getBytes("ISO8859_1"),"utf-8");
                        if(name.equals("name")){goods.Setname(value);}
                        if(name.equals("price")){goods.Setprice(value);}
                        if(name.equals("number")){int number = Integer.parseInt(value);goods.Setnumber(number);}
                        if(name.equals("class")){goods.Setclass(value);};
                    }  
                    else  
                    {  
                        String value = item.getName() ;  
                        int start = value.lastIndexOf(".");  
                        String filename = id + value.substring(start);  
                        goods.Setimg(filename);
                        System.out.println(path);
                        OutputStream out = new FileOutputStream(new File(path,filename));  
                        InputStream in = item.getInputStream() ;  
                        int length = 0 ;  
                        byte [] buf = new byte[1024] ;  
                        //System.out.println("获取上传文件的总共的容量："+item.getSize()); 
                        while( (length = in.read(buf) ) != -1)  
                        {  
                            out.write(buf, 0, length);                                
                        }  
                        in.close();  
                        out.close();  
                    }  
                }  
                goodsDao.add(goods);
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=dateFormat.format(now);
                String ip = (String)session.getAttribute("ip");
                Oplog record = new Oplog(provider, "销售员", time, "添加商品", ip);
                LogDao logDao = new LogDao();
                logDao.addoplog(record);
             }catch (FileUploadException e) {  
                e.printStackTrace();  
            }  
            catch (Exception e) {  
                e.printStackTrace();  
            }
            request.getRequestDispatcher("ShowServlet?action=manage_goods").forward(request,response);
        }
        if(action.equals("delete"))
        {
            GoodsDao goodsDao=new GoodsDao();
            String id=request.getParameter("id");
            goodsDao.delete(id);
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=dateFormat.format(now);
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            String ip = (String)session.getAttribute("ip");
            Oplog record = new Oplog(username, "销售员", time, "删除商品", ip);
            LogDao logDao = new LogDao();
            logDao.addoplog(record);
            request.getRequestDispatcher("ShowServlet?action=manage_goods&op=delete").forward(request,response);
        }
        if(action.equals("gotoupdate"))
        {
            GoodsDao goodsDao=new GoodsDao();
            String id=request.getParameter("id");
            Goods goods=new Goods();
            goods=goodsDao.Getgoods(id);
            request.setAttribute("information", goods);
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
        if(action.equals("update"))
        {
            GoodsDao goodsDao=new GoodsDao();
            Goods good=new Goods();
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String price=request.getParameter("price");
            String num=request.getParameter("number");
            int number = Integer.parseInt(num);
            good.Setid(id);
            good.Setname(name);
            good.Setprice(price);
            good.Setnumber(number);
            goodsDao.update(good);
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=dateFormat.format(now);
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            String ip = (String)session.getAttribute("ip");
            Oplog record = new Oplog(username, "管理员", time, "修改商品信息", ip);
            LogDao logDao = new LogDao();
            logDao.addoplog(record);
            request.getRequestDispatcher("ShowServlet?action=manage_goods&op=update").forward(request,response);
        }
    }
}