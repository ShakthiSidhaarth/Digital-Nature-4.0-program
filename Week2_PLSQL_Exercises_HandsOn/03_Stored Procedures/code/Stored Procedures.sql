CREATE TABLE accounts (
    account_id   NUMBER PRIMARY KEY,
    account_type VARCHAR2(20),  -- e.g., 'savings', 'current'
    balance      NUMBER
);
CREATE TABLE employees (
    emp_id      NUMBER PRIMARY KEY,
    name        VARCHAR2(100),
    department  VARCHAR2(50),
    salary      NUMBER
);
INSERT INTO accounts VALUES (201, 'savings', 10000);
INSERT INTO accounts VALUES (202, 'savings', 15000);
INSERT INTO accounts VALUES (203, 'current', 8000);
INSERT INTO accounts VALUES (204, 'savings', 5000);
INSERT INTO employees VALUES (1, 'Ravi', 'IT', 30000);
INSERT INTO employees VALUES (2, 'Meena', 'HR', 25000);
INSERT INTO employees VALUES (3, 'Arjun', 'IT', 32000);
INSERT INTO employees VALUES (4, 'Leela', 'Finance', 28000);

COMMIT;
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN
  FOR rec IN (SELECT account_id, balance FROM accounts WHERE account_type = 'savings') LOOP
    UPDATE accounts
    SET balance = balance + (balance * 0.01)
    WHERE account_id = rec.account_id;

    DBMS_OUTPUT.PUT_LINE('Interest added for account ID: ' || rec.account_id);
  END LOOP;

  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  p_department     VARCHAR2,
  p_bonus_percent  NUMBER
)
AS
BEGIN
  FOR rec IN (
    SELECT emp_id FROM employees WHERE department = p_department
  ) LOOP
    UPDATE employees
    SET salary = salary + (salary * p_bonus_percent / 100)
    WHERE emp_id = rec.emp_id;

    DBMS_OUTPUT.PUT_LINE('Bonus applied to employee ID: ' || rec.emp_id);
  END LOOP;

  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE TransferFunds (
  p_from_account  NUMBER,
  p_to_account    NUMBER,
  p_amount        NUMBER
)
AS
  v_balance NUMBER;
BEGIN
  SELECT balance INTO v_balance FROM accounts WHERE account_id = p_from_account;

  IF v_balance < p_amount THEN
    RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account.');
  END IF;
  UPDATE accounts SET balance = balance - p_amount WHERE account_id = p_from_account;
  UPDATE accounts SET balance = balance + p_amount WHERE account_id = p_to_account;

  COMMIT;

  DBMS_OUTPUT.PUT_LINE('Transferred ₹' || p_amount || ' from account ' || p_from_account || ' to account ' || p_to_account);
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/
BEGIN
  ProcessMonthlyInterest;
END;
/
BEGIN
  UpdateEmployeeBonus('IT', 10);
END;
/
BEGIN
  TransferFunds(202, 203, 3000);
END;
/