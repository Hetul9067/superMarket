package org.supermarket.factory;

import org.supermarket.builder.BakeryBuilder;
import org.supermarket.builder.FreshProductBuilder;
import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.products.Item;

public class FreshProductFactory extends AbstractFactory{


    @Override
    public ICategoryBuilder makeItem() {
        return new FreshProductBuilder(new Item());
    }
}
