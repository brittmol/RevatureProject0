DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;

-- one-to-many multiplicity btwn users and bank accounts
-- one-to-many multiplicity btwn bank accounts and transactions

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(40),
    last_name VARCHAR(40),
    username VARCHAR(40) UNIQUE,
    password VARCHAR(40),
    email VARCHAR(100),
    phone VARCHAR(10)
);

CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    account_type VARCHAR(40),
    balance DECIMAL(10,2),
    user_id INT REFERENCES users(id)
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    transaction_type VARCHAR(40),
    amount DECIMAL(10,2),
    account_id INT REFERENCES accounts(id)
);

SELECT * FROM users;
SELECT * FROM tasks;
SELECT * FROM transactions;
