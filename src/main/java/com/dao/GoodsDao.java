package com.dao;

import com.entity.Goods;
import com.entity.Log;
import com.dao.LogDao;
import com.utils.JdbcUtils;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class GoodsDao {
    public void add(Goods goods)
    {
        Map<String,String> dis2data = new HashMap<String,String>();
        dis2data.put("life","生活用品");
        dis2data.put("sport","运动");
        dis2data.put("food","食物");
        dis2data.put("electronic","电子产品");
        dis2data.put("cloth","衣服鞋子");
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        try{
            connection = JdbcUtils.getConnection();
            String sql = "insert into goods values(?,?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,goods.Getid());
            preparedStatement.setString(2,goods.Getname());
            preparedStatement.setString(3,goods.Getprice());
            preparedStatement.setInt(4,goods.Getnumber());
            preparedStatement.setString(5,goods.Getprovider());
            preparedStatement.setString(6,dis2data.get(goods.Getclass()));
            preparedStatement.setString(7,goods.Getimg());
            preparedStatement.setInt(8,goods.Getsell());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement, connection);
        }
    }
    public List<Goods> showmygoods(String provider)
    {
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where provider=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                String Class=resultSet.getString(6);
                String img=resultSet.getString(7);
                int sell = resultSet.getInt(8);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setclass(Class);
                p.Setimg(img);
                p.Setsell(sell);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }
    public List<Goods> showmygoods(String provider,String Class)
    {
        Map<String,String> dis2data = new HashMap<String,String>();
        dis2data.put("life","生活用品");
        dis2data.put("sport","运动");
        dis2data.put("food","食物");
        dis2data.put("electronic","电子产品");
        dis2data.put("cloth","衣服鞋子");
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where class like ? and provider = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, dis2data.get(Class));
            preparedStatement.setString(2, provider);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                String class_name=resultSet.getString(6);
                String img=resultSet.getString(7);
                int sell = resultSet.getInt(8);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setclass(class_name);
                p.Setimg(img);
                p.Setsell(sell);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }
    public List<Goods> showgoods()
    {
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                if(num==0)
                    continue;
                String provider=resultSet.getString(5);
                String Class=resultSet.getString(6);
                String img=resultSet.getString(7);
                int sell = resultSet.getInt(8);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setclass(Class);
                p.Setimg(img);
                p.Setsell(sell);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }

    public List<Goods> showgoods(String Class)
    {
        Map<String,String> dis2data = new HashMap<String,String>();
        dis2data.put("life","生活用品");
        dis2data.put("sport","运动");
        dis2data.put("food","食物");
        dis2data.put("electronic","电子产品");
        dis2data.put("cloth","衣服鞋子");
        if(Class.equals("生活用品"))Class = "life";
        if(Class.equals("运动"))Class = "sport";
        if(Class.equals("食物"))Class = "food";
        if(Class.equals("电子产品"))Class = "electronic";
        if(Class.equals("衣服鞋子"))Class = "cloth";
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where class like ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, dis2data.get(Class));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                if(num==0)
                    continue;
                String provider = resultSet.getString(5);
                String class_name=resultSet.getString(6);
                String img=resultSet.getString(7);
                int sell = resultSet.getInt(8);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setclass(class_name);
                p.Setimg(img);
                p.Setsell(sell);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }

    public void delete(String id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from goods where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public Goods Getgoods(String id)
    {
        Goods goods = new Goods();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                String provider=resultSet.getString(5);
                String Class=resultSet.getString(6);
                String img=resultSet.getString(7);
                int sell=resultSet.getInt(8);
                goods.Setid(id);
                goods.Setname(name);
                goods.Setnumber(num);;
                goods.Setprice(price);
                goods.Setprovider(provider);
                goods.Setclass(Class);
                goods.Setimg(img);
                goods.Setsell(sell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods;
    }
    public void update(Goods good) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update goods set name=?,price=?,number=? where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, good.Getname());
            preparedStatement.setString(2, good.Getprice());
            preparedStatement.setInt(3, good.Getnumber());
            preparedStatement.setString(4, good.Getid());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public void update(String id, int number, String op)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql=null;
            if(op.equals("sub"))
                sql = "update goods set number=number - ? where id=?;";
            else    
                sql = "update goods set number=number + ? where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public void update(String id, int number)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql=null;
            sql = "update goods set sell = ? where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public List<Goods> recommand(String user,String id)
    {
        List<Log> log_list=new ArrayList<Log>();
        LogDao logDao = new LogDao();
        log_list = logDao.getlog(user);
        int num[] = new int[5];
        for(int i=0;i<5;i++)num[i]=0;
        for(Log p :log_list)
        {
            if(p.Getclass().equals("运动"))num[0]++;
            if(p.Getclass().equals("生活用品"))num[1]++;
            if(p.Getclass().equals("食物"))num[2]++;
            if(p.Getclass().equals("电子产品"))num[3]++;
            if(p.Getclass().equals("衣服鞋子"))num[4]++;
        }
        int m = num[0];
        int index = 0;
        for(int i=1;i<5;i++)
        {
            if(m<num[i])
            {
                m=num[i];
                index = i;
            }
        }
        String Class ="";
        if(index==0)Class="运动";
        if(index==1)Class="生活用品";
        if(index==2)Class="食物";
        if(index==3)Class="电子产品";
        if(index==4)Class="衣服鞋子";
        List<Goods> goods_list=new ArrayList<Goods>();
        List<Goods> recommand_list=new ArrayList<Goods>();
        GoodsDao goodsDao = new GoodsDao();
        goods_list = goodsDao.showgoods(Class);
        System.out.println(goods_list.size());
        if(goods_list.size()>=4)
        {
            Random r = new Random();
            int random = r.nextInt(goods_list.size());
            Boolean flag = true;
            for(int i=random;recommand_list.size()<=4 && (i!=random || flag == true);i=(i+1)%goods_list.size())
            {
                flag = false;
                if(goods_list.get(i).Getid().equals(id))
                    continue;
                else
                    recommand_list.add(goods_list.get(i));                
            }
            return recommand_list;
        }
        else{
            for(int i=0;i<goods_list.size();i++)
            {
                recommand_list.add(goods_list.get(i));
            }
            Random r = new Random();
            goods_list = goodsDao.showgoods();
            int random = r.nextInt(goods_list.size());
            int start = random;
            Boolean flag =true;
            while(recommand_list.size()<4 && (random!=start || flag == true))
            {
                flag = false;
                if(goods_list.get(random).Getclass().equals(Class) || goods_list.get(random).Getid().equals(id))
                    random=(random+1)%goods_list.size();
                else{
                    recommand_list.add(goods_list.get(random));
                    random=(random+1)%goods_list.size();
                }
            }
            return recommand_list;
        }
    }
}
