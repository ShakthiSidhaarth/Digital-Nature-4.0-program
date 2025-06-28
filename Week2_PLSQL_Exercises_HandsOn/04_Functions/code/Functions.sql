SET SERVEROUTPUT ON;

CREATE TABLE accounts (
    account_id  NUMBER PRIMARY KEY,
    balance     NUMBER
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

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob DATE
) RETURN NUMBER
AS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

DECLARE
    v_age NUMBER;
BEGIN
    v_age := CalculateAge(TO_DATE('2000-06-24', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Age is: ' || v_age);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_amount         NUMBER,
    p_annual_rate    NUMBER,
    p_duration_years NUMBER
) RETURN NUMBER
AS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_emi          NUMBER;
BEGIN
    v_monthly_rate := p_annual_rate / 12 / 100;
    v_months := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        v_emi := p_amount / v_months;
    ELSE
        v_emi := p_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months) /
                 (POWER(1 + v_monthly_rate, v_months) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);
END;
/

DECLARE
    v_emi NUMBER;
BEGIN
    v_emi := CalculateMonthlyInstallment(500000, 7.5, 5);
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: Rs ' || v_emi);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id NUMBER,
    p_amount     NUMBER
) RETURN BOOLEAN
AS
    v_balance NUMBER;
BEGIN
    SELECT balance INTO v_balance FROM accounts WHERE account_id = p_account_id;

    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END;
/

DECLARE
    v_result BOOLEAN;
BEGIN
    v_result := HasSufficientBalance(301, 5000);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient balance.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance.');
    END IF;
END;
/

