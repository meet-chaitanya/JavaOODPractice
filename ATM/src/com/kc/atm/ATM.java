package com.kc.atm;

import com.kc.atm.transactions.Deposit;
import com.kc.atm.transactions.Withdrawal;

import java.util.Scanner;

public class ATM {
    private final ATMService atmService = new ATMService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("=== Welcome to the ATM ===");

        System.out.println("Enter userName: ");
        String userName = scanner.nextLine();

        System.out.println("Enter PIN: ");
        String pin = scanner.nextLine();

        if (!atmService.login(userName, pin)) {
            System.out.println("Authentication failed!");
            return;
        }

        while (true) {
            System.out.println("\n1. Balance\n2. Withdrawal\n3. Deposit\n4. Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    atmService.checkBalance(userName);
                    break;
                case "2":
                    System.out.println("Withdraw amount: ");
                    double wAmount = Double.parseDouble(scanner.nextLine());
                    atmService.processTransaction(userName, wAmount, new Withdrawal());
                    break;
                case "3":
                    System.out.println("Deposit amount: ");
                    double dAmount = Double.parseDouble(scanner.nextLine());
                    atmService.processTransaction(userName, dAmount, new Deposit());
                    break;
                case "4":
                    System.out.println("Thank you. Good Bye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
