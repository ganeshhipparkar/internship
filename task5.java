import java.util.*;

class Account {
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

   
    public Account(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

 
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

 
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

   
    public void showBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("--- Transaction History ---");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        Account account = new Account(name);

        boolean running = true;

        while (running) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1–5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmt = scanner.nextDouble();
                    account.deposit(depositAmt);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmt = scanner.nextDouble();
                    account.withdraw(withdrawAmt);
                    break;

                case 3:
                    account.showBalance();
                    break;

                case 4:
                    account.showTransactionHistory();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
