import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = FileManager.loadData();
    }

    public Account createAccount(String name, int pin, double initialDeposit) {

        String accNumber = Utils.generateAccountNumber();

        while (accounts.containsKey(accNumber)) {
            accNumber = Utils.generateAccountNumber();
        }

        Account newAccount = new Account(accNumber, name, pin, initialDeposit);
        accounts.put(accNumber, newAccount);

        FileManager.saveData(accounts);

        return newAccount;
    }

    public Account login(String accNumber, int pin) {

        if (!accounts.containsKey(accNumber)) {
            return null;
        }

        Account acc = accounts.get(accNumber);

        if (acc.checkPin(pin)) {
            return acc;
        }

        return null;
    }

    public Account getAccount(String accNumber) {
        return accounts.get(accNumber);
    }

    public void updateData() {
        FileManager.saveData(accounts);
    }
}
