import java.io.FileWriter;
import java.io.IOException;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            saveToFile();
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
            saveToFile();
        } else {
            System.out.println("Invalid withdraw amount or insufficient funds");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(accountNumber + ".txt")) {
            writer.write("Account Number: " + accountNumber + "\n");
            writer.write("Balance: " + balance + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the account.");
            e.printStackTrace();
        }
    }
}