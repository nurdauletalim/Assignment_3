package kz.iitu.assignment1;

import java.sql.*;
import java.util.Scanner;

public class ATM implements BankService{
    public ATM(Bank bank) {
       this.bank = bank;
    }
    Statement statement = null;
    Connection connection = null;
    private Account acc;
    private Bank bank;

    @Override
    public void menu(Account acc) {
        this.acc = acc;
        System.out.println("Welcome to bank!");
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
                System.out.print("Your remaining balance is:  ");
                System.out.println(acc.getBalance());
                return amount2;
            }
        }
        return amount2;
    }

    @Override
    public double topUP(double amount) {
        double amount2 = 0;
        boolean isTrue =false;
        while (!isTrue){
            if(amount <= acc.getBalance()){
                System.out.println(acc.getBalance());
                amount2 = acc.getBalance() + amount;
                acc.setBalance(amount2);
                System.out.print("Your remaining balance is:  ");
                System.out.println(acc.getBalance());
                return amount2;
            }
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

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public void init() throws SQLException {
        Connection connection = this.DBcon();
        ResultSet set = null;
        String query = "SELECT * FROM accounts";
        statement = connection.createStatement();
        set = statement.executeQuery(query);
        while (set.next()){
            Account acc = new Account(set.getInt(1), set.getDouble(2), set.getInt(3), set.getInt(4),
                    set.getString(5));
            bank.getAccounts().add(acc);
    }}



    @Override
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void destroy() throws SQLException{
        try {
            connection.close();
            if (connection != null) {
                System.out.println("Connection is closed!!!");
            }
            else {
                System.out.println("Something went wrong!!!");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    @Override
    public Connection DBcon() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successfull !!!");
            }
            else {
                System.out.println("Connection failed !!!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
            return connection;
    }
}
