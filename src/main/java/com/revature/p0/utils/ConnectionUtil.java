package com.revature.p0.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
//    public static Connection getConnection() throws SQLException, IOException {
//        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
//        Properties props = new Properties();
//        props.load(inputStream);
//
//        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
//
//        return conn;
//    }
}