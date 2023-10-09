package org.supermarket;

import org.supermarket.products.Item;
import org.supermarket.supplier.Catalog;
import org.supermarket.supplier.Supplier;

public class Main {

    public static void main(String[] args ){
        Supplier s1 = new Supplier();
        s1.generateCatalogs();
        for(Catalog c : s1.getCatalogs()){
            System.out.println();
            System.out.println();
            for(Item i : c.getItems()){
                System.out.println(i.getUniqueId());
                System.out.println(i.getName());
                System.out.println(i.getPrice());
                System.out.println(i.getCategory());
                System.out.println(i.getType());
                System.out.println(i.getDescription());
            }
        }


    }

}
