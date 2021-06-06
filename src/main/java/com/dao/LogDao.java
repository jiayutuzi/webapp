package com.dao;
import com.entity.Log;
import com.utils.JdbcUtils;
import com.entity.Loginlog;
import com.entity.Oplog;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;
public class LogDao {
    public void add(Log log)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into log values(?,?,?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,log.Gettime());
            preparedStatement.setString(2,log.Getuser());
            preparedStatement.setString(3,log.Getop());
            preparedStatement.setString(4,log.Getid());
            preparedStatement.setString(5,log.Getname());
            preparedStatement.setString(6,log.Getclass());
            preparedStatement.setInt(7,log.Getnumber());
            preparedStatement.setString(8,log.Getprovider());
            preparedStatement.setString(9,log.Getprice());
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
    public List<Log> showbrowlog(String provider)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where provider=? and op = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            preparedStatement.setString(2, "浏览");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String user=resultSet.getString(2);
                String op=resultSet.getString(3);
                String id=resultSet.getString(4);
                String name=resultSet.getString(5);
                String Class=resultSet.getString(6);
                int number=resultSet.getInt(7);
                String price=resultSet.getString(9);
                Log p = new Log();
                p.Settime(time);
                p.Setuser(user);
                p.Setop(op);
                p.Setid(id);
                p.Setname(name);
                p.Setclass(Class);
                p.Setnumber(number);
                p.Setprice(price);
                p.Setprovider(provider);
                log_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public List<Log> showbuylog(String provider)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where provider=? and op = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            preparedStatement.setString(2, "购买");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String user=resultSet.getString(2);
                String op=resultSet.getString(3);
                String id=resultSet.getString(4);
                String name=resultSet.getString(5);
                String Class=resultSet.getString(6);
                int number=resultSet.getInt(7);
                String price=resultSet.getString(9);
                Log p = new Log();
                p.Settime(time);
                p.Setuser(user);
                p.Setop(op);
                p.Setid(id);
                p.Setname(name);
                p.Setclass(Class);
                p.Setnumber(number);
                p.Setprice(price);
                p.Setprovider(provider);
                log_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public void addloginlog(Loginlog record)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into loginlog values(?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,record.Getusername());
            preparedStatement.setString(2,record.Getidentity());
            preparedStatement.setString(3,record.Getlogintime());
            preparedStatement.setString(4,record.Getlogouttime());
            preparedStatement.setString(5,record.Getip());
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
    public List<Loginlog> showlog()
    {
        List<Loginlog> log_list=new ArrayList<Loginlog>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from loginlog;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String username=resultSet.getString(1);
                String identity=resultSet.getString(2);
                String logintime=resultSet.getString(3);
                String logouttime=resultSet.getString(4);
                String ip=resultSet.getString(5);
                Loginlog p = new Loginlog(username, identity, logintime, logouttime, ip);
                log_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public List<Log> browlog(String provider)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where user=? and op = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            preparedStatement.setString(2, "浏览");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String user=resultSet.getString(2);
                String op=resultSet.getString(3);
                String id=resultSet.getString(4);
                String name=resultSet.getString(5);
                String Class=resultSet.getString(6);
                int number=resultSet.getInt(7);
                String price=resultSet.getString(9);
                Log p = new Log();
                p.Settime(time);
                p.Setuser(user);
                p.Setop(op);
                p.Setid(id);
                p.Setname(name);
                p.Setclass(Class);
                p.Setnumber(number);
                p.Setprice(price);
                p.Setprovider(provider);
                log_list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public List<Log> buylog(String provider)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where user=? and op = ?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            preparedStatement.setString(2, "购买");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String user=resultSet.getString(2);
                String op=resultSet.getString(3);
                String id=resultSet.getString(4);
                String name=resultSet.getString(5);
                String Class=resultSet.getString(6);
                int number=resultSet.getInt(7);
                String price=resultSet.getString(9);
                Log p = new Log();
                p.Settime(time);
                p.Setuser(user);
                p.Setop(op);
                p.Setid(id);
                p.Setname(name);
                p.Setclass(Class);
                p.Setnumber(number);
                p.Setprice(price);
                p.Setprovider(provider);
                log_list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public void addoplog(Oplog record)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into oplog values(?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,record.Getusername());
            preparedStatement.setString(2,record.Getidentity());
            preparedStatement.setString(3,record.Gettime());
            preparedStatement.setString(4,record.Getop());
            preparedStatement.setString(5,record.Getip());
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
    public List<Oplog> showoplog()
    {
        List<Oplog> log_list=new ArrayList<Oplog>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from oplog;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String username=resultSet.getString(1);
                String identity=resultSet.getString(2);
                String time=resultSet.getString(3);
                String op=resultSet.getString(4);
                String ip=resultSet.getString(5);
                Oplog p = new Oplog(username, identity, time, op, ip);
                log_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public List<Log> getlog(String user)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where user=?;";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String op=resultSet.getString(3);
                String id=resultSet.getString(4);
                String name=resultSet.getString(5);
                String Class=resultSet.getString(6);
                int number=resultSet.getInt(7);
                String provider = resultSet.getString(8);
                String price=resultSet.getString(9);
                Log p = new Log();
                p.Settime(time);
                p.Setuser(user);
                p.Setop(op);
                p.Setid(id);
                p.Setname(name);
                p.Setclass(Class);
                p.Setnumber(number);
                p.Setprice(price);
                p.Setprovider(provider);
                log_list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
}
