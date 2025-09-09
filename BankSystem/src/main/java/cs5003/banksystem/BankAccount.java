/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs5003.banksystem;

/**
 *
 * @author Ilir Tzaferai
 */
public class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private final String holderAddress;
    private final String openingDate;
    private double currentBalance;
    private final CustomLinkedList transactions;

    public BankAccount(String accountNumber, String holderName, String holderAddress, String openingDate, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderAddress = holderAddress;
        this.openingDate = openingDate;
        this.currentBalance = initialBalance;
        this.transactions = new CustomLinkedList();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void deposit(double amount, String date) {
        currentBalance += amount;
        transactions.add(new Transaction("Deposit", amount, date));
    }

    public boolean withdraw(double amount, String date) {
        if (currentBalance >= amount) {
            currentBalance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, date));
            return true;
        }
        if (amount > 0 && currentBalance >= amount) { // Ensure amount is valid and sufficient balance exists
            currentBalance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, date));
            return true;
        } else {
        return false; // Indicate that the withdrawal could not be completed
        }
    }
    
    
    public Transaction[] getLastFourTransactionsSorted() {
        Transaction[] transactionArray = transactions.toArray();
        insertionSort(transactionArray); // Custom insertion sort
        return transactionArray;
    }

    private void insertionSort(Transaction[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Transaction key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getAmount() < key.getAmount()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    @Override
    public String toString() {
        return String.format("Account Number: %s, Holder Name: %s, Address: %s, Opening Date: %s, Current Balance: %.2f",
                accountNumber, holderName, holderAddress, openingDate, currentBalance);
    }
}
    