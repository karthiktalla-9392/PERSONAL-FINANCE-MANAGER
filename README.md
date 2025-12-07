## 💰 Personal Finance Manager (Java)

A console-based Java application designed to help users track income, expenses, and generate reports. This project demonstrates strong programming fundamentals and advanced Java concepts including OOP, recursion, collections, file handling, exceptions, and optimized algorithm design.

## 📑 Table of Contents

1.[Overview](#Overview)

2.[Features](#Features)

3.[Course Outcomes](#Implemented)

4.[Project Structure](#ProjectStructure)

5.[Installation & Setup](#Installation&Setup)

6.[Usage](#Usage)

7.[Technical Highlights](#TechnicalHightlights)

8.[Contributors](#Contributors)

## 📘 Overview

The Personal Finance Manager allows users to log income and expenses, view transaction history, and generate summaries. Data is stored using file handling, ensuring persistence across program runs. The project was developed as part of an academic requirement to demonstrate knowledge of structured programming, OOP, and advanced Java concepts.

## ✔️ Features

Add, update and track income and expense entries

Generate summary reports (total income, total expense, balance)

Save and retrieve data using file storage

Input validation and exception handling

Clear menu-driven interface

Supports future scalability (DB storage, GUI, export options)

## 🎓 Course Outcomes Implemented
| Concept                            | Implementation in Project                         |
| ---------------------------------- | ------------------------------------------------- |
| Fundamental Programming Constructs | Variables, conditionals, loops                    |
| 1D & 2D Arrays                     | Used for storing UI menu and sample category data |
| Strings                            | Parsing transaction details                       |
| Bitwise Operations                 | Flag-based categorization                         |
| Recursion                          | Utility methods (e.g., summation)                 |
| OOP – Classes, Objects             | Main architecture                                 |
| Inheritance & Polymorphism         | Income & Expense extending Transaction            |
| Abstraction & Interfaces           | `Reportable` interface                            |
| Exception Handling                 | Input validation                                  |
| File I/O                           | Saving & loading financial records                |
| Generics                           | Used in collections for type safety               |
| Collections Framework              | `ArrayList` for storing transactions              |
| Optimized Algorithm Design         | Sorting & search on transaction records           |

## 📂 Project Structure

```
`
📁 Project Structure (File System Layout)
Assuming the project is named PersonalFinanceManager, the structure would look like this:

PersonalFinanceManager/
├── src/
│   ├── Expense.java
│   ├── FinanceService.java
│   ├── Income.java
│   ├── Main.java
│   ├── Reportable.java
│   └── Transaction.java
└── README.md
```

## 🛠 Installation & Setup
# Clone the repository
git clone https://github.com/your-username/personal-finance-manager.git

# Navigate into the project folder
cd personal-finance-manager

# Compile all Java files
javac *.java

# Run the application
java Main


## ▶️ Usage
1️⃣ Run the program
2️⃣ Choose actions from the menu:

Add Income 💵

Add Expense 🧾

View Transaction Reports 📊

3️⃣ Exit to save data automatically

Example menu:
1. Add Income
2. Add Expense
3. Display Report
4. Exit

Users can continue adding data until they choose Exit, with information stored automatically.

## 🚀 Technical Highlights

Object-Oriented Design (OOD) with inheritance (Income & Expense extend Transaction)

Interface-driven structure (Reportable)

Collections and Generics for efficient data storage

Recursion for iterative menu handling

File I/O for persistent storage

Exception handling for robust user experience

Bitwise validation checks for performance optimization

## 👥 Contributors
<table> <tr> <td align="center"><a href="https://github.com/karthiktalla-9392"><img src="https://github.com/karthiktalla-9392.png" width="90"/><br><sub><b>@karthiktalla-9392</b></sub></a></td> <td align="center"><a href="https://github.com/palavalasarahul-07"><img src="https://github.com/palavalasarahul-07.png" width="90"/><br><sub><b>@palavalasarahul-07</b></sub></a></td> <td align="center"><a href="https://github.com/bhavanakondakrindi"><img src="https://github.com/bhavanakondakrindi.png" width="90"/><br><sub><b>@bhavanakondakrindi</b></sub></a></td> <td align="center"><a href="https://github.com/pragnyavaleti"><img src="https://github.com/pragnyavaleti.png" width="90"/><br><sub><b>@pragnyavaleti</b></sub></a></td> <td align="center"><a href="https://github.com/sreshtapaladugu6"><img src="https://github.com/sreshtapaladugu6.png" width="90"/><br><sub><b>@sreshtapaladugu6</b></sub></a></td> <td align="center"><a href="https://github.com/mynenigeethika"><img src="https://github.com/mynenigeethika.png" width="90"/><br><sub><b>@mynenigeethika</b></sub></a></td> </tr> </table>
