import java.time.LocalDate;

public class Income extends Transaction {

    public Income(double amount, String category, String description, String paymentMethod) {
        super(amount, category, description, paymentMethod);
    }
    
    // Constructor to pass the date (based on month) to the superclass
    public Income(double amount, String category, String description, String paymentMethod, LocalDate date) {
        super(amount, category, description, paymentMethod, date);
    }

    @Override
    public boolean isIncome() {
        return true;
    }
}