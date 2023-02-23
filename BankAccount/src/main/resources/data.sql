DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS transaction;

CREATE TABLE account (
  id BIGINT PRIMARY KEY,
  balance DECIMAL(18,2)
);

CREATE TABLE transaction (
  id BIGINT PRIMARY KEY,
  amount DECIMAL(18,2),
  timestamp TIMESTAMP,
  type VARCHAR(20),
  account_id BIGINT,
  FOREIGN KEY (account_id) REFERENCES account(id)
);

INSERT INTO account (id, balance) VALUES (1, 1000.00);
INSERT INTO account (id, balance) VALUES (2, 5000.00);

