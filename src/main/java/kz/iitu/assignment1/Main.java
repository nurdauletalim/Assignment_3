package kz.iitu.assignment1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        BankService bankService = context.getBean("ATM",ATM.class);

        Account acc = bankService.getBank().getAccounts().get(0);
        bankService.menu(acc);
        ((ClassPathXmlApplicationContext) context).close();


    }
}
