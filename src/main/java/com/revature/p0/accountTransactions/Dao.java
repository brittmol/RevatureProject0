package com.revature.p0.accountTransactions;

import java.sql.SQLException;

public interface Dao<T, PK> {
    T read(PK pk);
    void create(T t) throws SQLException;
    void update(T t);
    void delete(T t);
}
