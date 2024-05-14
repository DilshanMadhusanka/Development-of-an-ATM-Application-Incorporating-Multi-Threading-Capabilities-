

package Test01;

import java.util.Scanner;

public class BankingApplication {

    public static void main(String[] args) {

        BankAccount obj = new BankAccount("SL DevCode", "SL00001");
        obj.showMenu();

        Scanner scanner = new Scanner(System.in);

        
        Thread userInputThread = new Thread(() -> {
            char option;
            do {
                System.out.println("---------------------------------------------------------------------------------------------------------------------- ");
                System.out.println("Enter Your Option");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                option = scanner.next().charAt(0);
                System.out.println("\n");

                switch (option) {
                    case 'A':
                        obj.checkBalance();
                        break;
                    case 'B':
                        obj.depositAmount(scanner);
                        break;
                    case 'C':
                        obj.withdrawAmount(scanner);
                        break;
                    case 'D':
                        obj.getPreviousTransaction();
                        break;
                    case 'E':
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid Option!! Please Enter Correct Option...");
                        break;
                }
            } while (option != 'E');
            scanner.close();
        });
        userInputThread.start();
    }

    static class BankAccount {
        int balance;
        int previousTransaction;
        String customerName;
        String customerId;

        BankAccount(String cname, String cid) {
            customerName = cname;
            customerId = cid;
        }

        void deposit(int amount) {
            if (amount != 0) {
                balance = balance + amount;
                previousTransaction = amount;
            }
        }

        void withdraw(int amount) {
            if (amount != 0) {
                balance = balance - amount;
                previousTransaction = -amount;
            }
        }

        void checkBalance() {
            System.out.println("*******************************************************");
            System.out.println("Balance = " + balance);
            System.out.println("*******************************************************");
            System.out.println("\n");
        }

        void getPreviousTransaction() {
            System.out.println("///////////////////////////////////////////////////////");
            if (previousTransaction > 0) {
                System.out.println("Deposited: " + previousTransaction);
            } else if (previousTransaction < 0) {
                System.out.println("Withdraw: " + Math.abs(previousTransaction));
            } else {
                System.out.println("No Transaction Occurred");
            }
            System.out.println("///////////////////////////////////////////////////////");
            System.out.println("\n");
        }

        void showMenu() {
            System.out.println("Welcome " + customerName);
            System.out.println("Your ID is " + customerId);
            System.out.println("\n");

            System.out.println("A : Check Your Balance");
            System.out.println("B : Deposit");
            System.out.println("C : Withdraw");
            System.out.println("D : Previous Transaction");
            System.out.println("E : Exit The System");
        }

        void depositAmount(Scanner scanner) {
            System.out.println("Enter an amount to deposit ");
            int depositAmount = scanner.nextInt();
            deposit(depositAmount);
            System.out.println("\n");
        }

        void withdrawAmount(Scanner scanner) {
            System.out.println("Enter an amount to withdraw ");
            int withdrawAmount = scanner.nextInt();
            withdraw(withdrawAmount);
            System.out.println("\n");
        }
    }
}
