import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final String[] VALID_PAYMENT_METHODS = {"cash", "card", "online"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceService service = new FinanceService();

        int choice;
        do {
            System.out.println("\n=== Personal Finance Manager ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Monthly Summary (Expense & Balance)");
            System.out.println("5. Edit Transaction");
            System.out.println("6. Delete Transaction");
            System.out.println("7. View Largest Expense Per Month");
            System.out.println("8. Search Expense by Category");
            System.out.println("9. Detect Duplicate Transactions");
            System.out.println("10. Search Transactions by Payment Method");
            System.out.println("11. View Overall Financial Summary");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");

            // Use try-catch for robust parsing of the menu choice
            try {
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    choice = 0;
                } else {
                    choice = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                choice = 0; // Invalid choice
            }

            switch (choice) {
                case 1 -> addIncome(scanner, service);
                case 2 -> addExpense(scanner, service);
                case 3 -> service.printAllTransactions();
                case 4 -> viewMonthlySummary(scanner, service);
                case 5 -> editTransaction(scanner, service);
                case 6 -> deleteTransaction(scanner, service);
                case 7 -> service.findLargestExpensePerMonth();
                case 8 -> searchExpenseByCategory(scanner, service);
                case 9 -> service.detectDuplicateTransactions();
                case 10 -> searchTransactionByPaymentMethod(scanner, service);
                case 11 -> viewOverallSummary(service);
                case 12 -> System.out.println("Goodbye! Total Yearly Savings: " + service.getTotalYearlySavings());
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 12.");
            }
        } while (choice != 12);

        scanner.close();
    }

    private static void addIncome(Scanner sc, FinanceService service) {
        System.out.println("\n--- Add Income ---");
        
        // Prompt for month first
        int month = getValidMonth(sc); 
        String monthName = service.getMonthName(month);

        double amount = getValidAmount(sc, "Income");
        if (amount <= 0) return;

        // Create LocalDate for the 1st day of the specified month in the current year
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, 1);
        
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        String paymentMethod = getValidPaymentMethod(sc);

        // Use the new constructor with date
        Transaction t = new Income(amount, category, desc, paymentMethod, date);
        service.addTransaction(t);
        System.out.println("Income added successfully for " + monthName + "."); 
    }

    private static void addExpense(Scanner sc, FinanceService service) {
        System.out.println("\n--- Add Expense ---");
        
        // 1. Get the month first to check the monthly balance
        int month = getValidMonth(sc);
        String monthName = service.getMonthName(month);

        // 2. Get the proposed expense amount
        double amount = getValidAmount(sc, "Expense");
        if (amount <= 0) return;
        
        // 3. Perform the monthly income check
        double monthlyIncome = service.getMonthlyIncome(month);
        double currentMonthlyExpense = service.getMonthlyExpense(month);
        double totalExpenseAfterAddition = currentMonthlyExpense + amount;

        if (totalExpenseAfterAddition > monthlyIncome) {
            System.out.printf("Invalid. Expense (%.2f) exceeds total income (%.2f) for %s.%n", 
                              totalExpenseAfterAddition, monthlyIncome, monthName);
            System.out.println("Transaction rejected.");
            return; // Exit the method without adding the transaction
        }
        // End of validation check

        // 4. If valid, proceed to collect other details and create transaction
        // Create LocalDate for the 1st day of the specified month in the current year
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, 1);
        
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        String paymentMethod = getValidPaymentMethod(sc);

        // Use the new constructor with date
        Transaction t = new Expense(amount, category, desc, paymentMethod, date);
        service.addTransaction(t);
        System.out.println("Expense added successfully for " + monthName + ".");
    }

    private static String getValidPaymentMethod(Scanner sc) {
        while (true) {
            System.out.print("Payment Method (Cash, Card, Online): ");
            String input = sc.nextLine().trim().toLowerCase();
            for (String validMethod : VALID_PAYMENT_METHODS) {
                if (validMethod.equals(input)) {
                    return input;
                }
            }
            System.out.println("Invalid payment method. Please use Cash, Card, or Online.");
        }
    }

    /**
     * Helper method to repeatedly prompt the user until a valid positive double is entered.
     */
    private static double getValidAmount(Scanner sc, String type) {
        while (true) {
            System.out.printf("%s Amount: ", type);
            try {
                double amount = Double.parseDouble(sc.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount must be a positive number.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the amount.");
            }
        }
    }
    
    /**
     * Helper method to repeatedly prompt the user until a valid month number (1-12) is entered.
     */
    private static int getValidMonth(Scanner sc) {
        while (true) {
            System.out.print("Enter Month Number (1=JANUARY, 12=DECEMBER): ");
            try {
                int month = Integer.parseInt(sc.nextLine());
                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    System.out.println("Invalid month number. Please enter a number between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number for the month.");
            }
        }
    }

    /**
     * Prompts the user for a month number (1-12) and displays the monthly summary.
     */
    private static void viewMonthlySummary(Scanner sc, FinanceService service) {
        while (true) {
            System.out.print("Enter month number (1=JANUARY, 12=DECEMBER): ");
            try {
                int month = Integer.parseInt(sc.nextLine());
                if (month >= 1 && month <= 12) {
                    service.printMonthlySummary(month); 
                    return;
                } else {
                    System.out.println("Invalid month number. Please enter a number between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number for the month.");
            }
        }
    }

    /**
     * Prompts the user for transaction ID and new details to edit a transaction.
     */
    private static void editTransaction(Scanner sc, FinanceService service) {
        System.out.print("Enter ID of transaction to edit: ");
        String id = sc.nextLine();

        Transaction t = service.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst().orElse(null);

        if (t == null) {
            System.out.println("Transaction with ID " + id + " not found.");
            return;
        }

        System.out.println("Editing Transaction: " + t.toReportLine());

        double newAmount = getValidAmount(sc, "New Amount");
        if (newAmount <= 0) return;

        System.out.print("New Category (Current: " + t.getCategory() + "): ");
        String newCategory = sc.nextLine();
        System.out.print("New Description (Current: " + t.getDescription() + "): ");
        String newDescription = sc.nextLine();
        
        String newPaymentMethod = getValidPaymentMethod(sc);


        service.editTransaction(id, newAmount, newCategory, newDescription, newPaymentMethod);
    }

    /**
     * Prompts the user for transaction ID to delete a transaction.
     */
    private static void deleteTransaction(Scanner sc, FinanceService service) {
        System.out.print("Enter ID of transaction to delete: ");
        String id = sc.nextLine();
        service.deleteTransaction(id);
    }

    /**
     * Prompts the user for a category to search expenses.
     */
    private static void searchExpenseByCategory(Scanner sc, FinanceService service) {
        System.out.print("Enter category to search expenses for: ");
        String category = sc.nextLine();
        service.searchExpenseByCategory(category);
    }

    /**
     * Prompts the user for a payment method to search transactions.
     */
    private static void searchTransactionByPaymentMethod(Scanner sc, FinanceService service) {
        System.out.print("Enter payment method to search (Cash, Card, Online): ");
        String method = sc.nextLine();
        service.searchTransactionByPaymentMethod(method);
    }

    /**
     * Prints the overall financial summary.
     */
    private static void viewOverallSummary(FinanceService service) {
        System.out.println("\n=== Overall Financial Summary ===");
        double income = service.getTotalIncome();
        double expense = service.getTotalExpense();
        double netBalance = income - expense;

        System.out.printf("Total Income: %.2f%n", income);
        System.out.printf("Total Expense: %.2f%n", expense);
        System.out.println("---------------------------------");
        System.out.printf("Net Balance (Income - Expense): %.2f%n", netBalance);
        System.out.printf("Savings Ratio: %.2f%% (Ratio: %.4f)%n", service.getSavingsRatio() * 100, service.getSavingsRatio());
        System.out.printf("Total Year-to-Date Savings (positive monthly balances): %.2f%n", service.getTotalYearlySavings());
        System.out.println("-----------------------------------");
    }
}