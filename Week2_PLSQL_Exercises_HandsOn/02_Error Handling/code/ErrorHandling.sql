SET SERVEROUTPUT ON;

CREATE TABLE accounts (
    account_id  NUMBER PRIMARY KEY,
    balance     NUMBER
);


CREATE TABLE employees (
    emp_id     NUMBER PRIMARY KEY,
    name       VARCHAR2(100),
    salary     NUMBER
);


CREATE TABLE customers (
    customer_id    NUMBER PRIMARY KEY,
    name           VARCHAR2(100),
    age            NUMBER,
    balance        NUMBER,
    interest_rate  NUMBER,
    IsVIP          VARCHAR2(5)
);

INSERT INTO accounts VALUES (101, 5000);
INSERT INTO accounts VALUES (102, 3000);
INSERT INTO accounts VALUES (103, 10000);

INSERT INTO employees VALUES (1, 'Arun', 30000);
INSERT INTO employees VALUES (2, 'Meena', 35000);
INSERT INTO employees VALUES (3, 'Sundar', 28000);


INSERT INTO customers VALUES (201, 'Kiran', 40, 12000, 9.5, 'FALSE');
INSERT INTO customers VALUES (202, 'Ritika', 29, 8000, 10.0, 'FALSE');

COMMIT;

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account   NUMBER,
    p_to_account     NUMBER,
    p_amount         NUMBER
)
AS
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance FROM accounts WHERE account_id = p_from_account;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds.');
    END IF;

    UPDATE accounts SET balance = balance - p_amount WHERE account_id = p_from_account;
    UPDATE accounts SET balance = balance + p_amount WHERE account_id = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Funds transferred from ' || p_from_account || ' to ' || p_to_account);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id     NUMBER,
    p_percent    NUMBER
)
AS
BEGIN
    UPDATE employees
    SET salary = salary + (salary * p_percent / 100)
    WHERE emp_id = p_emp_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee not found.');
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for employee ID: ' || p_emp_id);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id    NUMBER,
    p_name           VARCHAR2,
    p_age            NUMBER,
    p_balance        NUMBER,
    p_interest_rate  NUMBER
)
AS
BEGIN
    INSERT INTO customers (
        customer_id, name, age, balance, interest_rate, IsVIP
    ) VALUES (
        p_customer_id, p_name, p_age, p_balance, p_interest_rate, 'FALSE'
    );

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added successfully: ' || p_customer_id);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists - ' || p_customer_id);
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Unexpected error while adding customer: ' || SQLERRM);
END;
/

BEGIN
  SafeTransferFunds(103, 101, 2000);
END;
/

BEGIN
  SafeTransferFunds(102, 101, 6000);
END;
/

BEGIN
  UpdateSalary(2, 10);
END;
/

BEGIN
  UpdateSalary(99, 5);
END;
/

BEGIN
  AddNewCustomer(203, 'Nisha', 35, 9000, 8.7);
END;
/

BEGIN
  AddNewCustomer(201, 'Duplicate', 25, 5000, 9.0);
END;
/