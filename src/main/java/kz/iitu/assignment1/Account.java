package kz.iitu.assignment1;

public class Account {
    private int acc_id;
    private String name;
    private double balance;
    private int card;
    private int pass;


    public Account(int acc_id, double balance, int card, int pass, String name) {
        this.acc_id = acc_id;
        this.balance = balance;
        this.card = card;
        this.pass = pass;
        this.name = name;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", card=" + card +
                ", pass='" + pass + '\'' +
                '}';
    }
}


