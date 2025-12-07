import java.time.LocalDate;
import java.util.UUID;

public abstract class Transaction implements Reportable {

    private final String id;
    private final LocalDate date;
    private final double amount;
    private final String category;
    private final String description;

    protected Transaction(double amount, String category, String description) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.amount = amount;
        this.category = category;
        this.description = description;
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

    public abstract boolean isIncome();

    @Override
    public String toReportLine() {
        return String.join(",",
                id,
                date.toString(),
                isIncome() ? "INCOME" : "EXPENSE",
                category,
                String.valueOf(amount),
                description
        );
    }
}
