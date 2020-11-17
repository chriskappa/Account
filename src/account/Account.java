/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner; // importing User input packages

/**
 *
 * @author Xristakos
 */
public class Account {

    public static String anumber; // Declaring account number of the user
    public String name; // Creating variable with the name of the user
    public String adress; //Variable which holds users adress
    public String dateOfopening; //Declaring whhich date the account was created
    public double prevtransaction = 0; //Variable to check if the last transaction was deposit(positive number) or withdraw (negative number)
    static double balance; // Variable which holds the balance of the user

    Account(String acountname, String acountnum, String acadress) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // getting current date in order to change date of account opening  
        LocalDateTime now = LocalDateTime.now();
        anumber = acountnum;
        name = acountname;
        adress = acadress;
        dateOfopening = dtf.format(now); // seting up the date that the account was created
    }

    // Getter in order to print acount number
    static String get_anumber() {
        return anumber;

    }

    void set_anumber(String newNumber) {
        anumber = newNumber;
    }

    // Getter in order to print acount adress
    void get_adress() {
        System.out.println(adress);

    }

    void deposit(double amount) {

        if (amount > 0) {
            balance = balance + amount;
            prevtransaction = +amount;
            System.out.println("Your Balance has been updated succesfully, your new balance is " + balance);
        } else {
            System.out.println("Please input valid value bigger than 0!");
        }
    }

    void withdraw(double wamount) {
        if (wamount <= balance) {
            balance = balance - wamount;
            prevtransaction = -wamount;
            System.out.println("You have withdraw from your account " + wamount + " Pounds!");
        } else {
            System.out.println("ERROR:You dont have enough money to withdraw!");

        }

    }

    // Get balance function printing the balance for current user
    void get_balance() {
        System.out.println("-------------------------");
        System.out.println(name + " your balance is " + balance);
        System.out.println("-------------------------");
    }

    void get_transaction() {
        if (prevtransaction > 0) {
            System.out.println("Your last transaction was deposit and the amount was " + prevtransaction);
        } else if (prevtransaction < 0) {
            System.out.println("Your last transaction was withdraw and amount was " + Math.abs(prevtransaction)); //Math.abs  using it in order to return absolute value and not negative eg. -100
        } else {
            System.out.println("Something went wrong, please try again");
        }
    }

    // This function is printing the menu to user
    void menu() {
        double amount;
        char selected = '\0'; //the null character. It's a control character

        System.out.println("Welcome to LND MET BANK " + name);
        System.out.println("Your Account Number is: " + anumber);
        System.out.println("\n");
        System.out.println("------------------------------------");
        System.out.println("A) Check Account Balance");
        System.out.println("B) Deposit Cash To Acount");
        System.out.println("C) Withdraw Money From Account");
        System.out.println("D) Last Transaction");
        System.out.println("E) Exit From Aplication");
        System.out.println("------------------------------------");

        // Do while loop in order to repeat the menu and user inputs, until user select F(EXIT) 
        do {
            System.out.println('\n');
            Scanner scanner = new Scanner(System.in);
            System.out.println("--------------------------");
            System.out.println("Enter an option from A to F!");

            selected = scanner.next().charAt(0);

            switch (selected) {
                case 'A':
                    get_balance();
                    break;
                case 'B':
                    try {
                        System.out.println("Your current balance is " + balance + "$");
                        System.out.println("Please input the amount that you would like to deposit");
                        amount = scanner.nextDouble();
                        deposit(amount);
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("PLEASE TRY AGAIN WITH VALID NUMBERS!"); // Catching Error in case user input wrong values
                        menu();
                    }

                case 'C':
                    System.out.println("Your current balance is " + balance + "$");
                    System.out.println("Please input the amount that you would like to withdraw!");
                    amount = scanner.nextInt();
                    withdraw(amount);
                    scanner.nextLine();
                    break;

                case 'D':
                    get_transaction();
                    break;

                case 'E':
                    System.out.println("Thanks for using this aplication");
                    System.exit(0);

                default:
                    System.out.println("Something Went Wrong!");
                    break;

            }
        } while (selected != 'E');
    }

    public static void main(String[] args) {
        Account a1 = new Account("Christos", "a1234", "Test Adress");
        Account a4 = new Account("Bart", "BR12345", "Test Adress");
        Account a5 = new Account("Ricardo", "RR12345", "Test Adress");
        Account a2 = new Account("McGregor", "f1234", "Test Adress");
        Account a3 = new Account("Messi", "g12456", "Test Adress");
        Account a6 = new Account("Ola", "Londonmet", "Test Adress");
        a6.deposit(5000);
        System.out.println(a6.prevtransaction);
//        

    }

}
