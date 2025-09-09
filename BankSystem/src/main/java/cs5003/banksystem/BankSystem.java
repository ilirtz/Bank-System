/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cs5003.banksystem;

/**
 *
 * @author Ilir Tzaferai
 */

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
    private final ArrayList<BankAccount> accounts;

    public BankSystem() {
        accounts = new ArrayList<>();
    }

    public void createAccount(String accountNumber, String holderName, String holderAddress, String openingDate, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, holderName, holderAddress, openingDate, initialBalance);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system.");
            return;
        }
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    public void deleteAccount(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void updateTransaction(String accountNumber, String type, double amount, String date) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            if (type.equalsIgnoreCase("Deposit")) {
                account.deposit(amount, date);
                System.out.println("Deposit successful.");
            } else if (type.equalsIgnoreCase("Withdrawal")) {
                if (account.withdraw(amount, date)) {
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Invalid transaction type.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayLastFourTransactions(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Last Four Transactions (Sorted by Amount):");
            for (Transaction transaction : account.getLastFourTransactionsSorted()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BankSystem system = new BankSystem();
        Scanner scanner = new Scanner(System.in);
        BankSyGUI banks = new BankSyGUI();
        while (true) {
            System.out.println("\n--- Bank System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Delete Account");
            System.out.println("4. Update Transaction");
            System.out.println("5. Display Last Four Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter holder name: ");
                    String holderName = scanner.nextLine();
                    System.out.print("Enter holder address: ");
                    String holderAddress = scanner.nextLine();
                    System.out.print("Enter opening date: ");
                    String openingDate = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    system.createAccount(accountNumber, holderName, holderAddress, openingDate, initialBalance);
                }

                case 2 -> system.displayAccounts();

                case 3 -> {
                    System.out.print("Enter account number to delete: ");
                    String accountNumber = scanner.nextLine();
                    system.deleteAccount(accountNumber);
                }

                case 4 -> {
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter transaction type (Deposit/Withdrawal): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter transaction date: ");
                    String date = scanner.nextLine();
                    system.updateTransaction(accountNumber, type, amount, date);
                }

                case 5 -> {
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    system.displayLastFourTransactions(accountNumber);
                }

                case 6 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

