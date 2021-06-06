package com.dao;

import com.entity.User;
import com.utils.JdbcUtils;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;



public class UserDao{
    public User login(String username, String password,String identity){
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=? and password=? and identity=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,identity);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                u = new User();
                u.Setname(resultSet.getString("username"));
                u.Setpassword(resultSet.getString("password"));
                u.Setidentity(resultSet.getString("identity"));
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
        return u;
    }
    public User exist(String username,String identity){
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=? and identity= ?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,identity);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                u = new User();
                u.Setname(resultSet.getString("username"));
                u.Setemail(resultSet.getString("email"));
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
        return u;
    }

    public void addUser(User user){
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        try{
            connection = JdbcUtils.getConnection();
            String sql = "insert into user values(?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,user.Getname());
            preparedStatement.setString(2,user.Getpassword());
            preparedStatement.setString(3,user.Getidentity());
            preparedStatement.setString(4,user.Getemail());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement, connection);
        }
    }
    public List<User> GetSellers()
    {
        List<User> sellers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where identity=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,"seller");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String name=resultSet.getString(1);
                String password=resultSet.getString(2);
                String email=resultSet.getString(4);
                User seller = new User();
                seller.Setname(name);
                seller.Setpassword(password);
                seller.Setemail(email);
                sellers.add(seller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return sellers;
    }
    public void delseller(String name)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from user where username=? and identity=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, "seller");
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
    public void resetpw(String name,String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update user set password=? where username=? and identity=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, "seller");
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
}