
CREATE TABLE customers (
    customer_id    NUMBER PRIMARY KEY,
    name           VARCHAR2(100),
    age            NUMBER,
    balance        NUMBER,
    interest_rate  NUMBER,
    IsVIP          VARCHAR2(5)
);

INSERT INTO customers VALUES (101, 'Ravi Kumar', 65, 12000, 9.5, 'FALSE');
INSERT INTO customers VALUES (102, 'Sneha Rao', 35, 8000, 10.0, 'FALSE');
INSERT INTO customers VALUES (103, 'Manoj Verma', 70, 15000, 8.5, 'FALSE');
INSERT INTO customers VALUES (104, 'Asha Nair', 55, 9500, 9.0, 'FALSE');
INSERT INTO customers VALUES (105, 'Vikram Singh', 61, 7000, 11.0, 'FALSE');

DROP TABLE loans CASCADE CONSTRAINTS;

CREATE TABLE loans (
    loan_id     NUMBER PRIMARY KEY,
    customer_id NUMBER,
    due_date    DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

INSERT INTO loans VALUES (201, 101, SYSDATE + 15);  -- due in 15 days
INSERT INTO loans VALUES (202, 102, SYSDATE + 40);  -- not due soon
INSERT INTO loans VALUES (203, 103, SYSDATE + 5);   -- due in 5 days
INSERT INTO loans VALUES (204, 104, SYSDATE + 60);  -- not due 
COMMIT;

BEGIN
  FOR rec IN (
    SELECT customer_id, interest_rate
    FROM customers
    WHERE age > 60
  )
  LOOP
    UPDATE customers
    SET interest_rate = interest_rate - 1
    WHERE customer_id = rec.customer_id;

    DBMS_OUTPUT.PUT_LINE('Discount applied to customer ID: ' || rec.customer_id);
  END LOOP;
END;
/

BEGIN
  FOR rec IN (
    SELECT customer_id
    FROM customers
    WHERE balance > 10000
  )
  LOOP
    UPDATE customers
    SET IsVIP = 'TRUE'
    WHERE customer_id = rec.customer_id;

    DBMS_OUTPUT.PUT_LINE('VIP status granted to customer ID: ' || rec.customer_id);
  END LOOP;
END;
/

DECLARE
  v_customer_name VARCHAR2(100);
BEGIN
  FOR rec IN (
    SELECT loan_id, customer_id, due_date
    FROM loans
    WHERE due_date <= SYSDATE + 30
  )
  LOOP
    SELECT name INTO v_customer_name
    FROM customers
    WHERE customer_id = rec.customer_id;

    DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || rec.loan_id ||
                         ' for ' || v_customer_name ||
                         ' is due on ' || TO_CHAR(rec.due_date, 'DD-Mon-YYYY'));
  END LOOP;
END;
/

SET SERVEROUTPUT ON;


