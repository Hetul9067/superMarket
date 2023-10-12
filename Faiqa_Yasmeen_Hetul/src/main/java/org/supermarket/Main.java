package org.supermarket;

import org.supermarket.products.Item;
import org.supermarket.supermarket.Employee;
import org.supermarket.supermarket.SuperMarket;
import org.supermarket.supplier.Catalog;
import org.supermarket.supplier.Supplier;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args ){
        Supplier s1 = new Supplier();
        s1.generateCatalogs();
        SuperMarket sup1 = new SuperMarket(s1);
        sup1.addEmployee(new Employee("Faiqa", 10000, LocalDateTime.now()));
        sup1.addEmployee(new Employee("Yasmeen", 15000, LocalDateTime.now()));

        sup1.displaySuperMarket();



    }

}
