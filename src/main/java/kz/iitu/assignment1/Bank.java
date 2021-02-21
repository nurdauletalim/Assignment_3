package kz.iitu.assignment1;

import java.sql.*;
import java.util.List;

public class Bank {

    public Bank(){}
    private List<Account> accounts;
    private String ATMName;

    public Bank(List<Account> accounts, String ATMName) {
        this.accounts = accounts;
        this.ATMName = ATMName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setATMName(String ATMName) {
        this.ATMName = ATMName;
    }
}
