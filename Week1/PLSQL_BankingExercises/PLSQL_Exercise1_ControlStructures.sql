-- ============================================================
-- DN 5.0 Week 1 | Module 3 - PL/SQL Programming
-- Exercise 1: Control Structures
-- Schema: Banking system
-- ============================================================

-- ============================================================
-- SCHEMA SETUP (run once)
-- ============================================================

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    IsVIP CHAR(1) DEFAULT 'N',
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER REFERENCES Customers(CustomerID),
    AccountType VARCHAR2(20),
    Balance NUMBER
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER REFERENCES Accounts(AccountID),
    TransactionType VARCHAR2(20),
    Amount NUMBER,
    TransactionDate DATE DEFAULT SYSDATE
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER REFERENCES Customers(CustomerID),
    LoanAmount NUMBER,
    InterestRate NUMBER,
    DurationYears NUMBER,
    DueDate DATE
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Department VARCHAR2(50),
    Salary NUMBER
);

CREATE TABLE TxnAuditLog (
    LogID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    LogMessage VARCHAR2(200),
    LogDate DATE DEFAULT SYSDATE
);

-- ============================================================
-- SAMPLE DATA
-- ============================================================

INSERT INTO Customers VALUES (1, 'Anil Sharma',    DATE '1958-03-12', 15000, 'N', SYSDATE);
INSERT INTO Customers VALUES (2, 'Priya Mehta',    DATE '1990-07-22',  8500, 'N', SYSDATE);
INSERT INTO Customers VALUES (3, 'Ravi Kumar',     DATE '1965-11-05', 22000, 'N', SYSDATE);
INSERT INTO Customers VALUES (4, 'Sunita Rao',     DATE '1995-01-30',  4200, 'N', SYSDATE);
INSERT INTO Customers VALUES (5, 'Devendra Singh', DATE '1955-09-18', 30000, 'N', SYSDATE);

INSERT INTO Accounts VALUES (101, 1, 'Savings',  15000);
INSERT INTO Accounts VALUES (102, 2, 'Checking',  8500);
INSERT INTO Accounts VALUES (103, 3, 'Savings',  22000);
INSERT INTO Accounts VALUES (104, 4, 'Checking',  4200);
INSERT INTO Accounts VALUES (105, 5, 'Savings',  30000);

INSERT INTO Loans VALUES (201, 1,  50000, 8.5, 5,  SYSDATE + 20);
INSERT INTO Loans VALUES (202, 3, 100000, 7.2, 10, SYSDATE + 25);
INSERT INTO Loans VALUES (203, 5,  75000, 9.0, 7,  SYSDATE + 15);

INSERT INTO Employees VALUES (301, 'Meera Joshi',   'Sales', 45000);
INSERT INTO Employees VALUES (302, 'Arjun Nair',    'Sales', 48000);
INSERT INTO Employees VALUES (303, 'Kavita Reddy',  'IT',    60000);

COMMIT;

-- ============================================================
-- EXERCISE 1 - CONTROL STRUCTURES
-- ============================================================

-- Scenario 1: Apply 1% loan interest discount to customers over 60
SET SERVEROUTPUT ON;

DECLARE
    CURSOR cust_cursor IS
        SELECT CustomerID, Name, DOB FROM Customers;
    v_age NUMBER;
BEGIN
    FOR cust_rec IN cust_cursor LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, cust_rec.DOB) / 12);
        IF v_age > 60 THEN
            DBMS_OUTPUT.PUT_LINE(cust_rec.Name || ' (Age: ' || v_age ||
                ') qualifies for a 1% loan interest discount.');
        ELSE
            DBMS_OUTPUT.PUT_LINE(cust_rec.Name || ' (Age: ' || v_age ||
                ') does not qualify for the discount.');
        END IF;
    END LOOP;
END;
/

-- Scenario 2: Flag VIP customers if balance > 10000
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'Y' WHERE CustomerID = cust_rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE(cust_rec.Name || ' (Balance: ' || cust_rec.Balance ||
                ') is now flagged as VIP.');
        ELSE
            DBMS_OUTPUT.PUT_LINE(cust_rec.Name || ' (Balance: ' || cust_rec.Balance ||
                ') is not a VIP.');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Print reminders for loans due within 30 days
BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, c.Name, l.DueDate
        FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID
    ) LOOP
        IF loan_rec.DueDate <= SYSDATE + 30 THEN
            DBMS_OUTPUT.PUT_LINE('REMINDER: Loan ' || loan_rec.LoanID || ' for ' ||
                loan_rec.Name || ' is due on ' ||
                TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY'));
        END IF;
    END LOOP;
END;
/
