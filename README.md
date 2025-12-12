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

8.[Future Enhancements](#FutureEnhancements)

9.[Contributors](#Contributors)

## 📘 Overview

This Personal Finance Manager is a console-based Java application designed to help users track daily financial activities. It allows users to add income, record expenses, view transaction history, and generate financial summaries using object-oriented and modular programming principles.

The project is designed for academic learning, implementing major concepts from Java fundamentals to advanced OOP and file I/O, making it an excellent portfolio project.
## 🚀 Features

✔ Add & manage incomes

✔ Add & manage expenses

✔ Track all transactions

✔ Generate financial reports

✔ Uses interfaces & abstraction

✔ Supports inheritance and polymorphism

✔ Handles errors using exceptions

✔ Uses arrays, collections, and generics

✔ Includes file storage for persistence

✔ Clean object-oriented architecture

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

/PersonalFinanceManager
│
├── Main.java                 # Entry point of the application
├── FinanceService.java       # Core service logic
├── Transaction.java          # Base class for all transactions
├── Income.java               # Income model (inherits Transaction)
├── Expense.java              # Expense model (inherits Transaction)
├── Reportable.java           # Interface for report generation
└── data/                     # Directory to store files
    └── transactions.txt      # Saved financial data

```

## 🛠 Installation & Setup
Prerequisites

- Java 8+

- Any Java IDE (IntelliJ, VS Code, Eclipse) or terminal

Steps

1.Download or clone the repository:
```
git clone https://github.com/yourusername/personal-finance-manager.git
```
2.Open the folder in your IDE

3.Compile and run the program:
```
javac Main.java
java Main
```



## ▶️ Usage
Once you run the program, you can:

1️⃣ Add Income

User provides:

Amount

Source

Date

2️⃣ Add Expense

User provides:

- Amount

- Category

- Description

3️⃣ View All Transactions

Displays:

- Income list

- Expense list

- Running balance

4️⃣ Generate Report

- Using Reportable interface → outputs:

- Total income

- Total expenses

- Net savings

## 🚀 Technical Highlights

🔹 Object-Oriented Design

Well-structured classes with inheritance and interface usage.

🔹 Collections & Generics

Uses ArrayList<Transaction> for fast, dynamic storage.

🔹 File I/O

Stores data persistently across sessions.

🔹 Abstraction & Polymorphism

Transaction → parent class

Income and Expense → child classes

Reportable → interface for financial report generation

🔹 Exception Handling

Validates incorrect inputs gracefully.

## 📈 Future Enhancements

🔧 Add CSV/PDF export

🔧 Add category-wise monthly analytics

🔧 GUI version using JavaFX

🔧 Database support (MySQL / SQLite)

🔧 Charts & graphical reports

## 👥 Contributors
<table> <tr> <td align="center"><a href="https://github.com/karthiktalla-9392"><img src="https://github.com/karthiktalla-9392.png" width="90"/><br><sub><b>@karthiktalla-9392</b></sub></a></td> <td align="center"><a href="https://github.com/palavalasarahul-07"><img src="https://github.com/palavalasarahul-07.png" width="90"/><br><sub><b>@palavalasarahul-07</b></sub></a></td> <td align="center"><a href="https://github.com/bhavanakondakrindi"><img src="https://github.com/bhavanakondakrindi.png" width="90"/><br><sub><b>@bhavanakondakrindi</b></sub></a></td> <td align="center"><a href="https://github.com/pragnyavaleti"><img src="https://github.com/pragnyavaleti.png" width="90"/><br><sub><b>@pragnyavaleti</b></sub></a></td> <td align="center"><a href="https://github.com/sreshtapaladugu6"><img src="https://github.com/sreshtapaladugu6.png" width="90"/><br><sub><b>@sreshtapaladugu6</b></sub></a></td> <td align="center"><a href="https://github.com/mynenigeethika"><img src="https://github.com/mynenigeethika.png" width="90"/><br><sub><b>@mynenigeethika</b></sub></a></td> </tr> </table>
