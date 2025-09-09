/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs5003.banksystem;

/**
 *
 * @author Ilir Tzaferai
 */
public class CustomLinkedList {
    private class Node {
        Transaction data;
        Node next;

        Node(Transaction data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(Transaction transaction) {
        Node newNode = new Node(transaction);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;

        // Maintain only the last 4 transactions
        if (size > 4) {
            head = head.next; // Remove the oldest transaction
            size--;
        }
    }

    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            transactions[index++] = current.data;
            current = current.next;
        }
        return transactions;
    }

    public int getSize() {
        return size;
    }
}

