package com.revature.p0.accountTransactions;

// DAO = Data ASAccess Object
// contains the CRUD functionality

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoDao {
    private final Connection connection;

    public DemoDao(Connection connection) {
        this.connection = connection;
    }

    public DemoModel read(int id) throws SQLException {
        String sql = "SELECT * FROM demo WHERE id = ?";
        PreparedStatement prepSt = connection.prepareStatement(sql);
        prepSt.setInt(1, id);
        ResultSet results = prepSt.executeQuery();

        DemoModel model = new DemoModel();
        if(results.next()) {
            model.setId(results.getInt("id"));
            model.setMessage(results.getString("message"));

        }
        return model;
    }

    public void create(DemoModel model) throws SQLException {
        String sql = "INSERT INTO demo (id, message) VALUES (?, ?)";
        //       Statement stmt = connection.createStatement(); // this SQL Statement is hard coded & final
        PreparedStatement prepSt = connection.prepareStatement(sql); // this SQL can be parameterized
        prepSt.setString(1, model.getMessage());

        prepSt.executeUpdate();
    }

    public void update(DemoModel model) throws SQLException {
        String sql = "UPDATE demo SET message = ?";
        PreparedStatement prepSt = connection.prepareStatement(sql);
        prepSt.setString(1, model.getMessage());
        prepSt.setInt(2, model.getId());
        prepSt.executeUpdate();
        prepSt.getResultSet(); // TODO:
    }

    public void delete(DemoModel model) {

    }
}
