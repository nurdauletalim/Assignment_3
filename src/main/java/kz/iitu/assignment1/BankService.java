package kz.iitu.assignment1;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
@Component
public interface BankService {
    void menu(Account acc);
    boolean checkPIN(int passCode);
    void checkBalance();
    double withdraw(double amount);
    double topUP(double amount);
    void changePIN();
}
