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

INSERT INTO transaction (id, amount, timestamp, type, account_id) VALUES (1, 500.00, '2022-02-01 10:00:00', 'DEPOSIT', 1);
INSERT INTO transaction (id, amount, timestamp, type, account_id) VALUES (2, 200.00, '2022-02-02 11:00:00', 'WITHDRAWAL', 1);
INSERT INTO transaction (id, amount, timestamp, type, account_id) VALUES (3, 1000.00, '2022-02-03 12:00:00', 'DEPOSIT', 2);
