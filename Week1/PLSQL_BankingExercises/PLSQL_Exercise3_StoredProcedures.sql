-- ============================================================
-- DN 5.0 Week 1 | Module 3 - PL/SQL Programming
-- Exercise 3: Stored Procedures
-- ============================================================

SET SERVEROUTPUT ON;

-- Scenario 1: Apply 1% monthly interest to all Savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    FOR acc_rec IN (SELECT AccountID, Balance FROM Accounts WHERE AccountType = 'Savings') LOOP
        UPDATE Accounts SET Balance = Balance * 1.01 WHERE AccountID = acc_rec.AccountID;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END;
/

EXEC ProcessMonthlyInterest;

-- Scenario 2: Apply bonus percentage to a given department
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department    IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_department;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus applied to ' || p_department || ' department.');
END;
/

EXEC UpdateEmployeeBonus('Sales', 10);

-- Scenario 3: Transfer funds between accounts with balance check
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
    SELECT Balance INTO v_from_balance FROM Accounts WHERE AccountID = p_from_account;

    IF v_from_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer successful: ' || p_amount ||
            ' moved from account ' || p_from_account ||
            ' to account ' || p_to_account);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Transfer failed: insufficient balance in account ' || p_from_account);
    END IF;
END;
/

EXEC TransferFunds(101, 102, 5000);
