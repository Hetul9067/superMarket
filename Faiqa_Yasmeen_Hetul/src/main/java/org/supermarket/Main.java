package org.supermarket;

import org.supermarket.products.Item;
import org.supermarket.supermarket.SuperMarket;
import org.supermarket.supplier.Catalog;
import org.supermarket.supplier.Supplier;

public class Main {

    public static void main(String[] args ){
        Supplier s1 = new Supplier();
        s1.generateCatalogs();
        SuperMarket sup1 = new SuperMarket(s1);


        sup1.displaySuperMarket();



    }

}
