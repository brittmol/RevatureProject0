package com.revature.p0.daos;

import com.revature.p0.models.User;
import com.revature.p0.utils.ConnectionUtil;

import java.io.*;
import java.sql.*;

public class UserDAO {
    Connection conn;

    public UserDAO(Connection conn) throws SQLException, IOException {
        this.conn = ConnectionUtil.getConnection();
    }

    // READ: user (by id or username)
    public User getUserByid(Integer id) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet results = ps.executeQuery();

        User user = new User();
        if(results.next()) {
            user.setFirstName(results.getString("first_name"));
            user.setLastName(results.getString("last_name"));
            user.setUsername(results.getString("username"));
            user.setPassword(results.getString("password"));
            user.setEmail(results.getString("email"));
            user.setPhone(results.getString("phone"));
        }
        return user;

    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet results = ps.executeQuery();

        User user = new User();
        if(results.next()) {
            user.setFirstName(results.getString("first_name"));
            user.setLastName(results.getString("last_name"));
            user.setUsername(results.getString("username"));
            user.setPassword(results.getString("password"));
            user.setEmail(results.getString("email"));
            user.setPhone(results.getString("phone"));
        }
        return user;

    }

    // CREATE: user
    public User saveUser(User user) throws SQLException {
        if(user.getId() == null) {  // can only do this statement if id is Integer and not int
            // then it is a new user --> CREATE
            String sql = "INSERT INTO users(first_name, last_name, username, password, email, phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());

            ps.executeUpdate();
            ResultSet results = ps.getGeneratedKeys();
            if(results.next()) {
                user.setId(results.getInt(1));
            }

        } else {
            // then it is an existing user --> UPDATE
            String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ?, email = ?, phone = ?) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());

            ps.executeUpdate();
        }
        return user;
    }

    // UPDATE: user
    // DELETE: user

}