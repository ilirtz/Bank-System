/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs5003.banksystem;
/**
 *
 * @author hlias
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BankSyGUI {
    // Data structure to store accounts
    private final HashMap<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankSyGUI::new);
    }

    public BankSyGUI() {
        // Main Frame
        JFrame frame = new JFrame("Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 10, 10));

        // Buttons for menu options
        JButton createAccountBtn = new JButton("Create Account");
        JButton displayAccountsBtn = new JButton("Display Accounts");
        JButton deleteAccountBtn = new JButton("Delete Account");
        JButton updateTransactionBtn = new JButton("Update Transaction");
        JButton viewTransactionsBtn = new JButton("View Last Four Transactions");

        // Add action listeners
        createAccountBtn.addActionListener(e -> createAccount());
        displayAccountsBtn.addActionListener(e -> displayAccounts());
        deleteAccountBtn.addActionListener(e -> deleteAccount());
        updateTransactionBtn.addActionListener(e -> updateTransaction());
        viewTransactionsBtn.addActionListener(e -> viewLastFourTransactions());

        // Add buttons to the panel
        panel.add(createAccountBtn);
        panel.add(displayAccountsBtn);
        panel.add(deleteAccountBtn);
        panel.add(updateTransactionBtn);
        panel.add(viewTransactionsBtn);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Create Account functionality
    private void createAccount() {
        JTextField accountNumberField = new JTextField();
        JTextField holderNameField = new JTextField();
        JTextField holderAddressField = new JTextField();
        JTextField openingDateField = new JTextField();
        JTextField balanceField = new JTextField();

        Object[] message = {
            "Account Number:", accountNumberField,
            "Holder Name:", holderNameField,
            "Holder Address:", holderAddressField,
            "Opening Date:", openingDateField,
            "Current Balance:", balanceField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            String holderName = holderNameField.getText();
            String holderAddress = holderAddressField.getText();
            String openingDate = openingDateField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            accounts.put(accountNumber, new BankAccount(accountNumber, holderName, holderAddress, openingDate, balance));
            JOptionPane.showMessageDialog(null, "Account created successfully!");
        }
    }

    // Display Accounts functionality
    private void displayAccounts() {
        StringBuilder sb = new StringBuilder();
        for (BankAccount account : accounts.values()) {
            sb.append(account).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No accounts available.", "Accounts", JOptionPane.INFORMATION_MESSAGE);
    }

    // Delete Account functionality
    private void deleteAccount() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number to Delete:");
        if (accounts.remove(accountNumber) != null) {
            JOptionPane.showMessageDialog(null, "Account deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update Transaction functionality
    private void updateTransaction() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        BankAccount account = accounts.get(accountNumber);

        if (account != null) {
            String[] options = {"Deposit", "Withdrawal"};
            int transactionType = JOptionPane.showOptionDialog(null, "Select Transaction Type:", "Transaction",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            String amountStr = JOptionPane.showInputDialog("Enter Amount:");
            double amount = Double.parseDouble(amountStr);

            if (transactionType == 0) {
                account.deposit(amount);
            } else {
                if (!account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Transaction successful!");
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // View Last Four Transactions functionality
    private void viewLastFourTransactions() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        BankAccount account = accounts.get(accountNumber);

        if (account != null) {
            String transactions = account.getLastFourTransactions();
            JOptionPane.showMessageDialog(null, transactions, "Last Four Transactions", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // BankAccount class
    static class BankAccount {
        private final String accountNumber;
        private final String holderName;
        private final String holderAddress;
        private final String openingDate;
        private double balance;
        private final LinkedList<Transaction> transactions = new LinkedList<>();

        public BankAccount(String accountNumber, String holderName, String holderAddress, String openingDate, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.holderAddress = holderAddress;
            this.openingDate = openingDate;
            this.balance = balance;
        }

        public void deposit(double amount) {
            balance += amount;
            addTransaction("Deposit", amount);
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                addTransaction("Withdrawal", amount);
                return true;
            }
            return false;
        }

        private void addTransaction(String type, double amount) {
            if (transactions.size() == 4) {
                transactions.removeFirst();
            }
            transactions.add(new Transaction(type, amount));
        }

        public String getLastFourTransactions() {
            StringBuilder sb = new StringBuilder();
            for (Transaction t : transactions) {
                sb.append(t).append("\n");
            }
            return sb.length() > 0 ? sb.toString() : "No transactions available.";
        }

        @Override
        public String toString() {
            return "AccountNumber: " + accountNumber + ", HolderName: " + holderName + ", Balance: " + balance;
        }
    }

    // Transaction class
    static class Transaction {
        private final String type;
        private final double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + ": " + amount;
        }
    }
}
