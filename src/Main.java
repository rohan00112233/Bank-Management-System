import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter 4-digit PIN: ");
                int pin = sc.nextInt();

                if (!Utils.isValidPin(pin)) {
                    System.out.println("Invalid PIN! PIN must be 4 digits.");
                    continue;
                }

                System.out.print("Enter Initial Deposit: ");
                double deposit = sc.nextDouble();

                Account acc = bank.createAccount(name, pin, deposit);

                System.out.println("\nAccount Created Successfully!");
                System.out.println("Your Account Number: " + acc.getAccountNumber());

            } else if (choice == 2) {

                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();

                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();

                Account loggedIn = bank.login(accNo, pin);

                if (loggedIn == null) {
                    System.out.println("Login Failed! Invalid account or PIN.");
                    continue;
                }

                System.out.println("\nLogin Successful! Welcome " + loggedIn.getName());

                // Logged in menu
                while (true) {

                    System.out.println("\n===== ACCOUNT MENU =====");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transfer Money");
                    System.out.println("5. Transaction History");
                    System.out.println("6. Logout");
                    System.out.print("Enter choice: ");

                    int ch = sc.nextInt();

                    if (ch == 1) {
                        System.out.println("Current Balance: ₹" + loggedIn.getBalance());

                    } else if (ch == 2) {
                        System.out.print("Enter Deposit Amount: ");
                        double amt = sc.nextDouble();
                        loggedIn.deposit(amt);
                        bank.updateData();
                        System.out.println("Deposit Successful!");

                    } else if (ch == 3) {
                        System.out.print("Enter Withdraw Amount: ");
                        double amt = sc.nextDouble();

                        boolean ok = loggedIn.withdraw(amt);
                        bank.updateData();

                        if (ok) {
                            System.out.println("Withdraw Successful!");
                        } else {
                            System.out.println("Withdraw Failed! Insufficient Balance.");
                        }

                    } else if (ch == 4) {

                        sc.nextLine();
                        System.out.print("Enter Receiver Account Number: ");
                        String receiverAccNo = sc.nextLine();

                        Account receiver = bank.getAccount(receiverAccNo);

                        if (receiver == null) {
                            System.out.println("Receiver account not found!");
                            continue;
                        }

                        System.out.print("Enter Transfer Amount: ");
                        double amt = sc.nextDouble();

                        boolean ok = loggedIn.transfer(receiver, amt);
                        bank.updateData();

                        if (ok) {
                            System.out.println("Transfer Successful!");
                        } else {
                            System.out.println("Transfer Failed! Insufficient Balance.");
                        }

                    } else if (ch == 5) {

                        System.out.println("\n===== TRANSACTION HISTORY =====");

                        if (loggedIn.getTransactions().isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            for (Transaction t : loggedIn.getTransactions()) {
                                System.out.println(t);
                            }
                        }

                    } else if (ch == 6) {
                        System.out.println("Logged out successfully.");
                        break;

                    } else {
                        System.out.println("Invalid choice!");
                    }
                }

            } else if (choice == 3) {
                System.out.println("Thank you for using Bank Management System!");
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}


