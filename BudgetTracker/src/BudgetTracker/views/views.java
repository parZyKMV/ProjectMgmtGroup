package BudgetTracker.ui;

import BudgetTracker.auth.Authenticator;
import BudgetTracker.models.TransactionManager;
import BudgetTracker.models.FinancialGoals;
import java.util.Scanner;

public class BudgetTrackerTextUI {

    private Authenticator authenticator;
    private TransactionManager transactionManager;
    private FinancialGoals financialGoals;
    
    private String[] mainMenuOptions = {"Add Income", "Add Expense", "View Transactions", "Set Goals", "Exit"};
    private boolean isAuthenticated = false;
    private String currentScreen;

    public static void main(String[] args) {
        BudgetTrackerTextUI ui = new BudgetTrackerTextUI();
        ui.start();
    }

    public BudgetTrackerTextUI() {
        authenticator = new Authenticator();
        transactionManager = new TransactionManager();
        financialGoals = new FinancialGoals();
        currentScreen = "Login"; 
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            displayCurrentScreen();
            String userInput = scanner.nextLine();
            
            handleUserInput(userInput);
        }
    }

 
    private void displayCurrentScreen() {
        if (currentScreen.equals("Login")) {
            displayLoginScreen();
        } else if (currentScreen.equals("MainMenu")) {
            displayMainMenu();
        } else if (currentScreen.equals("AddIncome")) {
            displayAddIncomeScreen();
        } else if (currentScreen.equals("AddExpense")) {
            displayAddExpenseScreen();
        } else if (currentScreen.equals("TransactionHistory")) {
            displayTransactionHistoryScreen();
        } else if (currentScreen.equals("SetGoals")) {
            displaySetGoalsScreen();
        }
    }

    
    private void handleUserInput(String input) {
        if (currentScreen.equals("Login")) {
            if (input.equals("login")) {
                authenticateUser();
            }
        } else if (currentScreen.equals("MainMenu")) {
            int choice = Integer.parseInt(input);
            if (choice == 1) {
                currentScreen = "AddIncome";
            } else if (choice == 2) {
                currentScreen = "AddExpense";
            } else if (choice == 3) {
                currentScreen = "TransactionHistory";
            } else if (choice == 4) {
                currentScreen = "SetGoals";
            } else if (choice == 5) {
                System.exit(0);
            }
        } else if (currentScreen.equals("AddIncome") || currentScreen.equals("AddExpense")) {
            handleTransaction(input);
        } else if (currentScreen.equals("TransactionHistory") || currentScreen.equals("SetGoals")) {
            if (input.equals("back")) {
                currentScreen = "MainMenu";
            }
        }
    }

   
    private void authenticateUser() {
        isAuthenticated = true;
        currentScreen = "MainMenu";
    }

   
    private void displayLoginScreen() {
        System.out.println("Welcome to the Budget Tracker!");
        System.out.println("Please enter 'login' to authenticate.");
    }


    private void displayMainMenu() {
        System.out.println("Main Menu:");
        for (int i = 0; i < mainMenuOptions.length; i++) {
            System.out.println((i + 1) + ". " + mainMenuOptions[i]);
        }
        System.out.print("Select an option: ");
    }

 
    private void displayAddIncomeScreen() {
        System.out.println("Add Income Screen:");
        System.out.print("Enter income amount and description (e.g., '500 Salary'): ");
    }

   
    private void displayAddExpenseScreen() {
        System.out.println("Add Expense Screen:");
        System.out.print("Enter expense amount and description (e.g., '200 Groceries'): ");
    }

    
    private void displayTransactionHistoryScreen() {
        System.out.println("Transaction History:");
        // For now, display some dummy transactions
        System.out.println("1. Income: $500 (Salary)");
        System.out.println("2. Expense: $200 (Groceries)");
        System.out.print("Enter 'back' to return to the main menu: ");
    }


    private void displaySetGoalsScreen() {
        System.out.println("Set Financial Goals:");
        // For now, display some dummy goals
        System.out.println("1. Daily Spending Goal: $100");
        System.out.println("2. Monthly Spending Goal: $2000");
        System.out.print("Enter 'back' to return to the main menu: ");
    }

   
    private void handleTransaction(String input) {
        String[] transactionDetails = input.split(" ");
        if (transactionDetails.length == 2) {
            try {
                double amount = Double.parseDouble(transactionDetails[0]);
                String description = transactionDetails[1];
                if (currentScreen.equals("AddIncome")) {
                    transactionManager.addIncome(amount, description);
                    System.out.println("Income of $" + amount + " added for " + description);
                } else if (currentScreen.equals("AddExpense")) {
                    transactionManager.addExpense(amount, description);
                    System.out.println("Expense of $" + amount + " added for " + description);
                }
                currentScreen = "MainMenu";
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        } else {
            System.out.println("Invalid format. Please enter in the format 'amount description'.");
        }
    }
}

