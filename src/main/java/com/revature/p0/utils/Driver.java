package com.revature.p0.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Driver {
    public static void main(String[] args) throws IOException, SQLException {
        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(inputStream);

        ConnectionUtil.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
    }
}
