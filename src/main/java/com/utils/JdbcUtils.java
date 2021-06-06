package com.utils;

import java.sql.*;

public class JdbcUtils
{
    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";  
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/shop?serverTimezone=UTC";
    private static final String DB_User="root";
    private static final String DB_Password="jiayu";
    //private static final String DB_Password="Losorin123,,,";

    static Connection conn=null;
    public static Connection getConnection() throws Exception
    {
        try{
            Class.forName(JDBC_Driver);
            conn=DriverManager.getConnection(JDBC_URL, DB_User, DB_Password);
        }catch(Exception e)
        {
            throw e;
        }
        return conn;
    }
    public static void close(Statement statement, Connection connection){
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet result) {
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(result != null){
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}