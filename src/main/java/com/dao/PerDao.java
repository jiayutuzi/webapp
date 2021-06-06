package com.dao;
import com.entity.Cart;
import com.utils.JdbcUtils;
import com.entity.Performance;
import com.entity.Goods;
import com.dao.GoodsDao;
import java.text.DecimalFormat;  
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class PerDao{
    public void add(String name)
    {   
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into performance values(?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,"0");
            preparedStatement.setString(3,"0");
            preparedStatement.setString(4,"0");
            preparedStatement.setString(5,"0");
            preparedStatement.setString(6,"0");
            preparedStatement.setString(7,"0");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public void update(Cart cart)
    {
        double life =0;
        double food =0;
        double electronic=0;
        double cloth=0;
        double sport=0;
        double all=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from performance where name=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, cart.Getprovider());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sport = Double.parseDouble(resultSet.getString(2)); 
                life = Double.parseDouble(resultSet.getString(3));
                food = Double.parseDouble(resultSet.getString(4));
                electronic = Double.parseDouble(resultSet.getString(5));
                cloth = Double.parseDouble(resultSet.getString(6));
                all = Double.parseDouble(resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        GoodsDao goodsDao = new GoodsDao();
        Goods goods = new Goods();
        goods = goodsDao.Getgoods(cart.Getid());
        if(goods.Getclass().equals("运动"))sport = sport + Double.parseDouble(cart.Getprice())*cart.Getnumber();
        if(goods.Getclass().equals("生活用品"))life =life + Double.parseDouble(cart.Getprice())*cart.Getnumber();
        if(goods.Getclass().equals("食物"))food = food + Double.parseDouble(cart.Getprice())*cart.Getnumber();
        if(goods.Getclass().equals("电子产品"))electronic= electronic + Double.parseDouble(cart.Getprice())*cart.Getnumber();
        if(goods.Getclass().equals("鞋子衣服"))cloth = cloth + Double.parseDouble(cart.Getprice())*cart.Getnumber();
        all = sport+life+food+electronic+cloth;
        DecimalFormat df= new DecimalFormat("######0.00"); 
        try{
            connection = JdbcUtils.getConnection();
            String sql = "update performance set sport=?,life=?,food=?,electronic=?,cloth=?,allp=? where name=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,df.format(sport));
            preparedStatement.setString(2,df.format(life));
            preparedStatement.setString(3,df.format(food));
            preparedStatement.setString(4,df.format(electronic));
            preparedStatement.setString(5,df.format(cloth));
            preparedStatement.setString(6,df.format(all));
            preparedStatement.setString(7,cart.Getprovider());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement, connection);
        }
    }
    public List<Performance> showpPerformance()
    {

        List<Performance> performance_list=new ArrayList<Performance>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from performance;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String name = resultSet.getString(1);
                String sport = resultSet.getString(2);
                String life = resultSet.getString(3);
                String food = resultSet.getString(4);
                String electronic = resultSet.getString(5);
                String cloth = resultSet.getString(6);
                String all = resultSet.getString(7);
                Performance p = new Performance(name,life,sport,food,electronic,cloth,all);
                performance_list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return performance_list;
    }
}