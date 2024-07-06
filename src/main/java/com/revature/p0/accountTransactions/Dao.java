package com.revature.p0.accountTransactions;

import java.sql.SQLException;

public interface Dao<DemoModel> {
    DemoModel read(int id) throws SQLException;
    void create(DemoModel model) throws SQLException;
    void update(DemoModel model) throws SQLException;
    void delete(DemoModel model) throws SQLException;
}
