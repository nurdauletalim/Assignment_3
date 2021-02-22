package kz.iitu.assignment1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Scanner;

@Component("atm")
public class ATM implements BankService{
    @Value("atm.ATMName")
    private String ATMName;

    private Account acc;
    private Bank bank;

    @Override
    public void menu(Account acc) {
        this.acc = acc;
        System.out.println("Welcome to " + ATMName + " bank!");
        Scanner sc = new Scanner(System.in);
        int password;
        boolean pValid = false;
        double amount;
        do {
            System.out.print("Enter password: ");
            password = sc.nextInt();
            if (checkPIN(password) == true) {
                pValid = true;
                break;
            } else {
                System.out.println("Wrong password, try again");
            }
        } while (!pValid);
        System.out.println("Hello, " + acc.getName() + "! Choose operation: ");
        boolean valid2 = true;
        while (valid2){
            System.out.println("[1] Check balance\n[2] Withdraw\n[3] Top up\n[4] Change PIN\n[5] Exit");
            int ch = sc.nextInt();
            if(ch == 1){
                checkBalance();
                break;
            }
            if(ch == 2 ){
                System.out.println("Enter amount of money:");
                amount = sc.nextDouble();
                double balances =withdraw(amount);
                break;
            }
            if(ch == 3 ){
                System.out.println("Enter amount of money:");
                amount = sc.nextDouble();
                double balances = topUP(amount);
                break;
            }
            if(ch == 4 ){
                changePIN();
            }
            if(ch == 5 ){
                System.exit(0);
            }
        }

    }
    @Override
    public boolean checkPIN(int passCode){
        boolean isTrue = false;
            if (passCode == acc.getPass()) {
                isTrue = true;
            }
        return isTrue;

    }

    @Override
    public void checkBalance() {
            System.out.println("Your balance: " + acc.getBalance());
    }

    @Override
    public double withdraw(double amount) {
        double amount2 = 0;
        boolean isTrue =false;
        while (!isTrue){
            if(amount <= acc.getBalance()){
                System.out.println(acc.getBalance());
                amount2 = acc.getBalance() - amount;
                acc.setBalance(amount2);
                System.out.print("Operation succeed.\n" + "Your remaining balance is:  ");
                System.out.println(acc.getBalance());
                return amount2;
            }
            else System.out.println("Error! You don't have enough money.\nYour balance: " +acc.getBalance() );
        }
        return amount2;
    }

    @Override
    public double topUP(double amount) {
        double amount2 = 0;
        boolean isTrue =false;
        while (!isTrue){
                System.out.println(acc.getBalance());
                amount2 = acc.getBalance() + amount;
                acc.setBalance(amount2);
                System.out.print("Your remaining balance is:  ");
                System.out.println(acc.getBalance());
                return amount2;
        }
        return amount2;
    }

    @Override
    public void changePIN() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new PIN: ");
        int pin = scan.nextInt();
        acc.setPass(pin);
        System.out.println("PIN code successfully changed! Your new PIN: " + pin);
    }

}
