BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE AuditLog CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

CREATE TABLE Customers (
    customer_id     NUMBER PRIMARY KEY,
    name            VARCHAR2(100),
    balance         NUMBER,
    LastModified    DATE
);

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/


INSERT INTO Customers VALUES (1, 'Kiran', 10000, NULL);
COMMIT;

UPDATE Customers SET balance = balance + 500 WHERE customer_id = 1;

SELECT * FROM Customers WHERE customer_id = 1;

CREATE TABLE Transactions (
    transaction_id   NUMBER PRIMARY KEY,
    account_id       NUMBER,
    amount           NUMBER,
    type             VARCHAR2(10),  -- 'deposit' or 'withdraw'
    created_at       DATE DEFAULT SYSDATE
);

CREATE TABLE AuditLog (
    log_id           NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    transaction_id   NUMBER,
    action           VARCHAR2(50),
    action_time      DATE
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (transaction_id, action, action_time)
    VALUES (:NEW.transaction_id, 'INSERTED', SYSDATE);
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance
    FROM Customers
    WHERE customer_id = :NEW.account_id;

    IF :NEW.type = 'withdraw' THEN
        IF :NEW.amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds available balance.');
        END IF;
    ELSIF :NEW.type = 'deposit' THEN
        IF :NEW.amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type.');
    END IF;
END;
/

INSERT INTO Transactions (transaction_id, account_id, amount, type)
VALUES (1001, 1, 1000, 'deposit');

UPDATE Customers SET balance = balance + 1000 WHERE customer_id = 1;

INSERT INTO Transactions (transaction_id, account_id, amount, type)
VALUES (1002, 1, 2000, 'withdraw');

UPDATE Customers SET balance = balance - 2000 WHERE customer_id = 1;
SELECT * FROM Customers;
SELECT * FROM Transactions;
SELECT * FROM AuditLog;
