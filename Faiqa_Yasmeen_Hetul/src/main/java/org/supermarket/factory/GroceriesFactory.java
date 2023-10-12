package org.supermarket.factory;

import org.supermarket.builder.BakeryBuilder;
import org.supermarket.builder.GroceriesItemBuilder;
import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.products.Item;

public class GroceriesFactory extends AbstractFactory{
    @Override
    public ICategoryBuilder makeItem() {
        return new GroceriesItemBuilder(new Item());
    }
}
