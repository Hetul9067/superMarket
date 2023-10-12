package org.supermarket.factory;

import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.products.Item;

public abstract class AbstractFactory {
    public static AbstractFactory factoryMethod(String category){
        AbstractFactory factory = null;
        switch(category){
            case "grocery":
                factory = new GroceriesFactory();
                break;
            case "bakery" :
                factory = new BakeryFactory();
            case "freshproduct" :
                factory = new FreshProductFactory();
        }
        return factory;
    }

    public abstract ICategoryBuilder makeItem();
}
