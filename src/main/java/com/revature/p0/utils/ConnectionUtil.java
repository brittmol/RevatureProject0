package com.revature.p0.utils;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException, IOException {
        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(inputStream);

        return DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
    }

}