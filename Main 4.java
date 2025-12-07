import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceService service = new FinanceService();   // will be file 4 later

        int choice;
        do {
            System.out.println("=== Personal Finance Manager ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addIncome(scanner, service);
                case 2 -> addExpense(scanner, service);
                case 3 -> service.printAllTransactions();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void addIncome(Scanner sc, FinanceService service) {
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();

        Transaction t = new Income(amount, category, desc);
        service.addTransaction(t);
    }

    private static void addExpense(Scanner sc, FinanceService service) {
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();

        Transaction t = new Expense(amount, category, desc);
        service.addTransaction(t);
    }
}
