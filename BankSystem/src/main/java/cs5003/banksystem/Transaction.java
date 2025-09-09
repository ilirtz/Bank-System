/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs5003.banksystem;

/**
 *
 * @author Ilir Tzaferai
 */
public class Transaction {
    private final String type; // "Deposit" or "Withdrawal"
    private final double amount;
    private final String date;

    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Amount: %.2f, openingDate: %s", type, amount, date);
    }
}

