package com.revature.p0.accountTransactions;

// DAO = Data ASAccess Object
// contains the CRUD functionality

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemoDao<T, PK> implements Dao<T, PK> {
    private final Connection connection;

    public DemoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T read(PK pk) {
        // TODO:
        return null;
    }

    @Override
    public void create(T t) throws SQLException {
        String sql = "INSERT INTO demo (id, message) VALUES (?, ?)";
        //       Statement stmt = connection.createStatement(); // this SQL Statement is hard coded & final
        PreparedStatement prepSt = connection.prepareStatement(sql); // this SQL can be parameterized
        prepSt.setString(1, "Hello World!");

        prepSt.executeUpdate();
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {

    }
}
