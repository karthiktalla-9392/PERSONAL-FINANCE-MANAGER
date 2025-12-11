import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Transaction implements Reportable {

    private final String id;
    private final LocalDate date;
    private double amount; // Made non-final for editing
    private String category; // Made non-final for editing
    private String description; // Made non-final for editing
    private String paymentMethod; // New field

    // Original Constructor (Kept for completeness, though not used in Main)
    protected Transaction(double amount, String category, String description, String paymentMethod) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.paymentMethod = paymentMethod;
    }

    // Constructor to allow setting a specific date based on month input
    protected Transaction(double amount, String category, String description, String paymentMethod, LocalDate date) {
        this.id = UUID.randomUUID().toString();
        this.date = date; // Use the provided date
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public abstract boolean isIncome();

    @Override
    public String toReportLine() {
        return String.join(",",
                id,
                date.toString(),
                isIncome() ? "INCOME" : "EXPENSE",
                category,
                String.valueOf(amount),
                description,
                paymentMethod
        );
    }

    /**
     * Checks for a duplicate transaction based on amount, category, description, and type.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
               category.equalsIgnoreCase(that.category) &&
               description.equalsIgnoreCase(that.description) &&
               isIncome() == that.isIncome();
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, category.toLowerCase(), description.toLowerCase(), isIncome());
    }
}