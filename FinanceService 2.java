import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinanceService {

    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void printAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("ID,DATE,TYPE,CATEGORY,AMOUNT,DESCRIPTION");
        for (Transaction t : transactions) {
            System.out.println(t.toReportLine());
        }
    }

    public double getTotalIncome() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            if (t.isIncome()) {
                sum += t.getAmount();
            }
        }
        return sum;
    }

    public double getTotalExpense() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            if (!t.isIncome()) {
                sum += t.getAmount();
            }
        }
        return sum;
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("totalIncome,totalExpense,savingsRatio");
            writer.newLine();
            writer.write(income + "," + expense + "," + savingsRatio);
            writer.newLine();
            System.out.println("Monthly summary written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing summary: " + e.getMessage());
        }
    }
}
