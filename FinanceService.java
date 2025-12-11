import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class FinanceService {

    private final List<Transaction> transactions = new ArrayList<>();
    private double totalYearlySavings = 0.0; 

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void printAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("ID,DATE,TYPE,CATEGORY,AMOUNT,DESCRIPTION,PAYMENT_METHOD");
        for (Transaction t : transactions) {
            System.out.println(t.toReportLine());
        }
    }
    
    // *** NEW HELPER METHOD: Get total income for a specific month ***
    public double getMonthlyIncome(int monthNumber) {
        return transactions.stream()
                .filter(t -> t.isIncome() && t.getDate().getMonthValue() == monthNumber)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    
    // *** NEW HELPER METHOD: Get total expense for a specific month ***
    public double getMonthlyExpense(int monthNumber) {
        return transactions.stream()
                .filter(t -> !t.isIncome() && t.getDate().getMonthValue() == monthNumber)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> !t.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getMonthlyNetBalance(int monthNumber) {
        return getMonthlyIncome(monthNumber) - getMonthlyExpense(monthNumber);
    }

    /**
     * Calculates and prints the total expense and net balance for a specific month (Output 4).
     */
    public void printMonthlySummary(int monthNumber) {
        String monthName = getMonthName(monthNumber);

        double monthlyIncome = getMonthlyIncome(monthNumber);
        double monthlyExpense = getMonthlyExpense(monthNumber);

        double netBalance = monthlyIncome - monthlyExpense;

        System.out.println("--- Monthly Summary for " + monthName + " ---");
        System.out.printf("Total Income: %.2f%n", monthlyIncome);
        System.out.printf("Total Expense: %.2f%n", monthlyExpense);
        System.out.printf("Net Balance (Savings for month): %.2f%n", netBalance);
        System.out.println("----------------------------------------------");
    }

    /**
     * Utility to convert month number to name.
     */
    public String getMonthName(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            return Month.of(monthNumber).toString(); 
        }
        return "Invalid Month";
    }

    public double getTotalYearlySavings() {
        // Recalculate total yearly savings from all monthly balances (positive only)
        totalYearlySavings = 0.0;
        for (int i = 1; i <= 12; i++) {
            double net = getMonthlyNetBalance(i);
            if (net > 0) {
                totalYearlySavings += net;
            }
        }
        return totalYearlySavings;
    }

    public double getSavingsRatio() {
        double income = getTotalIncome();
        double expense = getTotalExpense();
        if (income <= 0) {
            return 0.0;
        }
        return (income - expense) / income;
    }

    // Very simple CSV writer for monthly_summary.csv
    public void writeMonthlySummary(String fileName) {
        double income = getTotalIncome();
        double expense = getTotalExpense();
        double savingsRatio = getSavingsRatio();
        double yearlySavings = getTotalYearlySavings();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("totalIncome,totalExpense,netBalance,savingsRatio,totalYearlySavings");
            writer.newLine();
            writer.write(String.format(Locale.US, "%.2f,%.2f,%.2f,%.2f,%.2f", income, expense, (income - expense), savingsRatio, yearlySavings));
            writer.newLine();
            System.out.println("Monthly summary written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing summary: " + e.getMessage());
        }
    }

    /**
     * Edits an existing transaction by ID.
     */
    public boolean editTransaction(String id, double newAmount, String newCategory, String newDescription, String newPaymentMethod) {
        for (Transaction t : transactions) {
            if (t.getId().equals(id)) {
                // NOTE: A more complex system would check the monthly balance after editing, 
                // but for simplicity, we'll allow editing a transaction without the check.
                t.setAmount(newAmount);
                t.setCategory(newCategory);
                t.setDescription(newDescription);
                t.setPaymentMethod(newPaymentMethod);
                System.out.println("Transaction " + id + " updated successfully.");
                return true;
            }
        }
        System.out.println("Transaction with ID " + id + " not found.");
        return false;
    }

    /**
     * Deletes a transaction by ID.
     */
    public boolean deleteTransaction(String id) {
        boolean removed = transactions.removeIf(t -> t.getId().equals(id));
        if (removed) {
            System.out.println("Transaction " + id + " deleted successfully.");
        } else {
            System.out.println("Transaction with ID " + id + " not found.");
        }
        return removed;
    }

    /**
     * Finds and prints the largest expense for each month (Output 7).
     */
    public void findLargestExpensePerMonth() {
        System.out.println("\n--- Largest Expense Per Month ---");
        transactions.stream()
                .filter(t -> !t.isIncome())
                .collect(Collectors.groupingBy(t -> t.getDate().getMonthValue()))
                .forEach((month, monthlyExpenses) -> { 
                    Transaction largest = monthlyExpenses.stream()
                            .max(Comparator.comparingDouble(Transaction::getAmount))
                            .orElse(null);
                    if (largest != null) {
                        // Displaying month name instead of number
                        System.out.printf("%s: %.2f (%s)%n", getMonthName(month), largest.getAmount(), largest.getDescription()); 
                    }
                });
        System.out.println("-----------------------------------");
    }

    /**
     * Searches for expenses by category (case-insensitive).
     */
    public void searchExpenseByCategory(String category) {
        String searchCat = category.trim().toLowerCase();

        List<Transaction> matchingExpenses = transactions.stream()
                .filter(t -> !t.isIncome() && t.getCategory().trim().toLowerCase().contains(searchCat))
                .collect(Collectors.toList());

        System.out.println("\n--- Expenses by Category: \"" + category + "\" ---");
        if (matchingExpenses.isEmpty()) {
            System.out.println("No expenses found for category: " + category);
            return;
        }

        System.out.println("ID,DATE,TYPE,CATEGORY,AMOUNT,DESCRIPTION,PAYMENT_METHOD");
        matchingExpenses.forEach(t -> System.out.println(t.toReportLine()));
        System.out.printf("Total for Category: %.2f%n", matchingExpenses.stream().mapToDouble(Transaction::getAmount).sum());
        System.out.println("---------------------------------------------");
    }

    /**
     * Detects and prints duplicate transactions.
     */
    public void detectDuplicateTransactions() {
        System.out.println("\n--- Duplicate Transactions Detected ---");

        Map<Transaction, List<Transaction>> map = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t, // Group by the transaction object (uses equals/hashCode)
                        Collectors.toList()
                ));

        boolean found = false;
        for (List<Transaction> list : map.values()) {
            if (list.size() > 1) {
                found = true;
                System.out.println("\nDUPLICATE SET:");
                System.out.println("ID,DATE,TYPE,CATEGORY,AMOUNT,DESCRIPTION,PAYMENT_METHOD");
                list.forEach(t -> System.out.println(t.toReportLine()));
            }
        }

        if (!found) {
            System.out.println("No duplicate transactions found (based on amount, category, description, and type).");
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Searches for transactions by payment method.
     */
    public void searchTransactionByPaymentMethod(String method) {
        String searchMethod = method.trim().toLowerCase();

        List<Transaction> matchingTransactions = transactions.stream()
                .filter(t -> t.getPaymentMethod().trim().toLowerCase().equals(searchMethod))
                .collect(Collectors.toList());

        System.out.println("\n--- Transactions by Payment Method: \"" + method + "\" ---");
        if (matchingTransactions.isEmpty()) {
            System.out.println("No transactions found for payment method: " + method);
            return;
        }

        System.out.println("ID,DATE,TYPE,CATEGORY,AMOUNT,DESCRIPTION,PAYMENT_METHOD");
        matchingTransactions.forEach(t -> System.out.println(t.toReportLine()));

        double totalIncome = matchingTransactions.stream().filter(Transaction::isIncome).mapToDouble(Transaction::getAmount).sum();
        double totalExpense = matchingTransactions.stream().filter(t -> !t.isIncome()).mapToDouble(Transaction::getAmount).sum();

        System.out.printf("Total Income via %s: %.2f%n", method, totalIncome);
        System.out.printf("Total Expense via %s: %.2f%n", method, totalExpense);
        System.out.println("-------------------------------------------------");
    }
}