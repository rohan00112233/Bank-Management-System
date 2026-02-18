import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String type;
    private double amount;
    private String message;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String message) {
        this.type = type;
        this.amount = amount;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[" + dateTime + "] " + type + " | Amount: ₹" + amount + " | " + message;
    }
}
