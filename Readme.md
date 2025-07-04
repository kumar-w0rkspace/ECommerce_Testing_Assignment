# 🛰️ Ziegler Aerospace - Selenium Automation Assignment

This project is a complete end-to-end automation test suite for the **[Automation Test Store](https://automationteststore.com/)** website. It was developed as part of the **Selenium Automation Assignment** for Ziegler Aerospace.

---

## 📌 Assignment Overview

The assignment provided a real-world scenario involving:

1. Dynamic homepage interaction and product verification.
2. Intelligent cart operations with stock and UI validation.
3. Checkout simulation using CSV test data.
4. Negative testing for form validation.
5. Logging and reporting of test outcomes and failures.

---

## ✅ Implementation Summary

### 🔹 1. Homepage & Category Verification

- Dynamically fetched all main category names using Selenium `@FindBy` list.
- Printed the category list in both console logs and `report.txt`.
- Randomly clicked a main category and subcategory using reusable utility logic.
- Asserted that the selected category displays at least **three products**.
- Captured a screenshot if the product count was insufficient.

### 🔹 2. Product Selection & Cart Addition

- Used a loop to add **two random in-stock products** to the cart.
- Captured and logged:
  - Product name
  - Price
  - Stock availability
  - Product URL
- Logged skipped products (e.g., out of stock or no cart button) into `report.txt`.
- Captured screenshots for any skipped or failed additions.

### 🔹 3. Cart & Checkout Workflow

- Validated that the cart contains **two added items**.
- Verified that the **total cart price** = Item1 + Item2 + Shipping.
- Initiated checkout and redirected to account registration.
- Filled the **registration form dynamically** using `testdata.csv` and OpenCSV.
- Selected random region/state and fixed country for realism.

### 🔹 4. Negative Scenario (Validation Testing)

- Emptied a required field (`Last Name`) to simulate user error.
- Submitted the form and asserted that an **error message alert** was triggered.
- Captured a screenshot of the validation error.
- Designed to handle validation alerts gracefully without hardcoding messages.

### 🔹 5. Reporting & Logging

- Created a centralized `report.txt` to log:
  - Product success/failures
  - Categories visited
  - Any skipped items
- Captured screenshots for all major failures (validation error, product add failure, etc.).
- Used custom `ScreenShotUtil` and `ReportUtils` classes to encapsulate utilities.

---

## 🛠️ Tech Stack

- **Java** with **Selenium WebDriver**
- **TestNG** for test execution
- **OpenCSV** for test data input
- **Log4j** for logging
- **Apache Commons IO** for file handling
- **POM (Page Object Model)** architecture
- **Custom Utility Classes** for reporting and screenshots



## 📂 Project Structure
---

├── src
│ ├── main
│ │ └── java
│ │ ├── pages/      # Page Objects (POM)
│ │ └── utils/      # Utilities (CSV, Screenshot, Report)
│ └── test
│ └── java
│ └── tests/        # Full test execution (FullTest.java)
├── testdata.csv    # Dummy registration data
├── report.txt      # Runtime execution log
├── screenshots/    # Auto-captured error images
└── testng.xml      # TestNG configuration
└── demo-results    # Temporary testresult files, logs

---

👨‍💻 Author

Kumar
Automation Testing | Java + Selenium | POM | TestNG | CSV-driven Testing