BEGIN EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS'; 
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN NULL; ELSE RAISE; END IF; 
END;
/

BEGIN EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS'; 
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN NULL; ELSE RAISE; END IF; 
END;
/

BEGIN EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS'; 
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN NULL; ELSE RAISE; END IF; 
END;
/

CREATE TABLE Customers (
  customer_id   NUMBER PRIMARY KEY,
  name          VARCHAR2(100),
  balance       NUMBER,
  LastModified  DATE
);

CREATE TABLE Employees (
  emp_id      NUMBER PRIMARY KEY,
  name        VARCHAR2(100),
  salary      NUMBER,
  department  VARCHAR2(50)
);

CREATE TABLE Accounts (
  acc_id       NUMBER PRIMARY KEY,
  customer_id  NUMBER,
  acc_type     VARCHAR2(20),
  balance      NUMBER
);

CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
  PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
  FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) IS
  BEGIN
    INSERT INTO Customers (customer_id, name, balance, LastModified)
    VALUES (p_id, p_name, p_balance, SYSDATE);
  END;

  PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) IS
  BEGIN
    UPDATE Customers
    SET name = p_name,
        balance = p_balance
    WHERE customer_id = p_id;
  END;

  FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
    v_balance NUMBER;
  BEGIN
    SELECT balance INTO v_balance
    FROM Customers
    WHERE customer_id = p_id;
    RETURN v_balance;
  END;

END CustomerManagement;
/
CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
  PROCEDURE UpdateEmployee(p_id NUMBER, p_salary NUMBER, p_dept VARCHAR2);
  FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) IS
  BEGIN
    INSERT INTO Employees VALUES (p_id, p_name, p_salary, p_dept);
  END;

  PROCEDURE UpdateEmployee(p_id NUMBER, p_salary NUMBER, p_dept VARCHAR2) IS
  BEGIN
    UPDATE Employees
    SET salary = p_salary,
        department = p_dept
    WHERE emp_id = p_id;
  END;

  FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER IS
    v_salary NUMBER;
  BEGIN
    SELECT salary INTO v_salary FROM Employees WHERE emp_id = p_id;
    RETURN v_salary * 12;
  END;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
  PROCEDURE CloseAccount(p_acc_id NUMBER);
  FUNCTION GetTotalBalance(p_cust_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
  BEGIN
    INSERT INTO Accounts VALUES (p_acc_id, p_cust_id, p_type, p_balance);
  END;

  PROCEDURE CloseAccount(p_acc_id NUMBER) IS
  BEGIN
    DELETE FROM Accounts WHERE acc_id = p_acc_id;
  END;

  FUNCTION GetTotalBalance(p_cust_id NUMBER) RETURN NUMBER IS
    v_total NUMBER := 0;
  BEGIN
    SELECT NVL(SUM(balance), 0) INTO v_total
    FROM Accounts
    WHERE customer_id = p_cust_id;
    RETURN v_total;
  END;

END AccountOperations;
/
-- Add customer
BEGIN
  CustomerManagement.AddCustomer(1, 'Kiran', 10000);
END;
/

-- Update customer
BEGIN
  CustomerManagement.UpdateCustomer(1, 'Kiran Kumar', 12000);
END;
/

-- Get customer balance
DECLARE
  bal NUMBER;
BEGIN
  bal := CustomerManagement.GetCustomerBalance(1);
  DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || bal);
END;
/

-- Hire employee
BEGIN
  EmployeeManagement.HireEmployee(101, 'John', 30000, 'HR');
END;
/

-- Calculate annual salary
DECLARE
  sal NUMBER;
BEGIN
  sal := EmployeeManagement.CalculateAnnualSalary(101);
  DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || sal);
END;
/

-- Open account
BEGIN
  AccountOperations.OpenAccount(201, 1, 'Savings', 5000);
END;
/

-- Get total balance across accounts
DECLARE
  total NUMBER;
BEGIN
  total := AccountOperations.GetTotalBalance(1);
  DBMS_OUTPUT.PUT_LINE('Total Account Balance: ' || total);
END;
/
