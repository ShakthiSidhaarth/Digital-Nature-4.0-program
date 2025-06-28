BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

CREATE TABLE Loans (
    loan_id         NUMBER PRIMARY KEY,
    customer_id     NUMBER,
    amount          NUMBER,
    interest_rate   NUMBER
);

INSERT INTO Loans VALUES (101, 1, 50000, 7.5);
INSERT INTO Loans VALUES (102, 1, 150000, 8.0);
COMMIT;

INSERT INTO Transactions (transaction_id, account_id, amount, type, created_at)
VALUES (1003, 1, 3000, 'deposit', SYSDATE);

INSERT INTO Transactions (transaction_id, account_id, amount, type, created_at)
VALUES (1004, 1, 1500, 'withdraw', SYSDATE);
COMMIT;

DECLARE
  CURSOR GenerateMonthlyStatements IS
    SELECT c.customer_id, c.name, t.transaction_id, t.amount, t.type, t.created_at
    FROM Customers c
    JOIN Transactions t ON c.customer_id = t.account_id
    WHERE TRUNC(t.created_at, 'MM') = TRUNC(SYSDATE, 'MM')
    ORDER BY c.customer_id, t.created_at;

  v_cust_id Customers.customer_id%TYPE;
  v_name Customers.name%TYPE;
  v_trans_id Transactions.transaction_id%TYPE;
  v_amount Transactions.amount%TYPE;
  v_type Transactions.type%TYPE;
  v_date Transactions.created_at%TYPE;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Monthly Statement - ' || TO_CHAR(SYSDATE, 'Month YYYY'));

  OPEN GenerateMonthlyStatements;
  LOOP
    FETCH GenerateMonthlyStatements INTO v_cust_id, v_name, v_trans_id, v_amount, v_type, v_date;
    EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

    DBMS_OUTPUT.PUT_LINE('Customer: ' || v_name || ' | ID: ' || v_cust_id);
    DBMS_OUTPUT.PUT_LINE('  -> Transaction: ' || v_trans_id || 
                         ', Type: ' || v_type || 
                         ', Amount: ' || v_amount || 
                         ', Date: ' || TO_CHAR(v_date, 'DD-Mon-YYYY'));
  END LOOP;
  CLOSE GenerateMonthlyStatements;
END;
/

DECLARE
  CURSOR ApplyAnnualFee IS
    SELECT customer_id, name, balance FROM Customers;

  v_id Customers.customer_id%TYPE;
  v_name Customers.name%TYPE;
  v_balance Customers.balance%TYPE;
  v_fee CONSTANT NUMBER := 500;
BEGIN
  OPEN ApplyAnnualFee;
  LOOP
    FETCH ApplyAnnualFee INTO v_id, v_name, v_balance;
    EXIT WHEN ApplyAnnualFee%NOTFOUND;

    UPDATE Customers
    SET balance = balance - v_fee
    WHERE customer_id = v_id;

    DBMS_OUTPUT.PUT_LINE('Annual fee of Rs ' || v_fee || ' applied to ' || v_name);
  END LOOP;
  CLOSE ApplyAnnualFee;
  COMMIT;
END;
/

DECLARE
  CURSOR UpdateLoanInterestRates IS
    SELECT loan_id, amount, interest_rate FROM Loans;

  v_loan_id Loans.loan_id%TYPE;
  v_amount Loans.amount%TYPE;
  v_rate Loans.interest_rate%TYPE;
  v_new_rate NUMBER;
BEGIN
  OPEN UpdateLoanInterestRates;
  LOOP
    FETCH UpdateLoanInterestRates INTO v_loan_id, v_amount, v_rate;
    EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

    IF v_amount > 100000 THEN
      v_new_rate := v_rate + 1.5;
    ELSE
      v_new_rate := v_rate + 1.0;
    END IF;

    UPDATE Loans
    SET interest_rate = v_new_rate
    WHERE loan_id = v_loan_id;

    DBMS_OUTPUT.PUT_LINE('Loan ID ' || v_loan_id || 
                         ' updated: ' || v_rate || '% → ' || v_new_rate || '%');
  END LOOP;
  CLOSE UpdateLoanInterestRates;
  COMMIT;
END;
/
