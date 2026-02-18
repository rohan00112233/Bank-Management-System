import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private String accountNumber;
    private String name;
    private int pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber, String name, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();

        // Add initial deposit transaction
        if (balance > 0) {
            transactions.add(new Transaction(
                    "INITIAL_DEPOSIT",
                    balance,
                    "Initial deposit while creating account"
            ));
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public boolean checkPin(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, "Money deposited successfully"));
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {
            transactions.add(new Transaction("WITHDRAW", amount, "Failed: Insufficient Balance"));
            return false;
        }

        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount, "Money withdrawn successfully"));
        return true;
    }

    public boolean transfer(Account receiver, double amount) {

        if (amount > balance) {
            transactions.add(new Transaction("TRANSFER", amount, "Failed: Insufficient Balance"));
            return false;
        }

        balance -= amount;
        receiver.balance += amount;

        transactions.add(new Transaction("TRANSFER", amount,
                "Transferred to Account: " + receiver.accountNumber));

        receiver.transactions.add(new Transaction("RECEIVED", amount,
                "Received from Account: " + this.accountNumber));

        return true;
    }
}
