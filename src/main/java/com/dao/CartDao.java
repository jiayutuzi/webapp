package com.dao;

import com.entity.Cart;
import com.utils.JdbcUtils;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;  
import java.sql.*;
public class CartDao {
    public void add(Cart item)
    {
        String img=null;
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JdbcUtils.getConnection();
            String sql = "select img from goods where id = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,item.Getid());
            resultSet = preparedStatement.executeQuery();
            if((resultSet.next()))
                img=resultSet.getString(1);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement, connection);
        }
        try{
            connection = JdbcUtils.getConnection();
            String sql = "insert into cart values(?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,item.Getid());
            preparedStatement.setString(2,item.Getname());
            preparedStatement.setString(3,item.Getprice());
            preparedStatement.setString(4,item.Getprovider());
            preparedStatement.setInt(5,item.Getnumber());
            preparedStatement.setString(6,item.Getuser());
            preparedStatement.setString(7,img);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement, connection);
        }
    }
    public List<Cart> showcart(String user)
    {
        List<Cart> cart_list=new ArrayList<Cart>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from cart where user=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                String provider = resultSet.getString(4);
                int num=resultSet.getInt(5);
                String img = resultSet.getString(7);
                Cart p=new Cart();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setuser(user);
                p.Setimg(img);
                cart_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return cart_list;
    }
    public String Getprice(String user)
    {
        double sum=0;
        String sum_price=null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select price,number from cart where user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                String p = rs.getString(1);
                int number=rs.getInt(2);
                double price=Double.parseDouble(p);
                sum+=price*number;
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            sum_price=df.format(sum);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return sum_price;
    }
    public Cart exist(Cart item)
    {
        Cart result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from cart where id=? and user= ?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,item.Getid());
            preparedStatement.setString(2,item.Getuser());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = new Cart();
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                String provider = resultSet.getString(4);
                int num=resultSet.getInt(5);
                String user=resultSet.getString(6);
                String img = resultSet.getString(7);
                result.Setid(id);
                result.Setname(name);
                result.Setprice(price);
                result.Setprovider(provider);
                result.Setnumber(num);
                result.Setuser(user);
                result.Setimg(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }
        finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return result;
    }
    public void update(Cart item)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update cart set number=?, price=? where id = ? and user = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setInt(1, item.Getnumber());
            preparedStatement.setString(2, item.Getprice());
            preparedStatement.setString(3, item.Getid());
            preparedStatement.setString(4, item.Getuser());
            
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
    public void delete(String id,String user)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from cart where id=? and user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, user);
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
    public void delete(String user)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from cart where user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
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
    public void deleteprovider(String provider)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from cart where provider=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
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
    public Cart Getcart(String id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cart result=null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from cart where id=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                result = new Cart();
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                String provider = resultSet.getString(4);
                int num=resultSet.getInt(5);
                String user=resultSet.getString(6);
                String img = resultSet.getString(7);
                result.Setid(id);
                result.Setname(name);
                result.Setprice(price);
                result.Setprovider(provider);
                result.Setnumber(num);
                result.Setuser(user);
                result.Setimg(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return result;
    }
}
