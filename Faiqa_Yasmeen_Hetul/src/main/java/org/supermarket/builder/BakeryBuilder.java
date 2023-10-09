package org.supermarket.builder;

import org.supermarket.products.Item;

public class BakeryBuilder implements ICategoryBuilder{

    private Item item;

    public BakeryBuilder(){
        this.item = new Item();
    }
    @Override
    public void buildUniqueId(int id) {
        this.item.setUniqueId(id);
    }

    @Override
    public void buildItem(String name, double price, String type) {
        this.item.setName(name);
        this.item.setPrice(price);
        this.item.setType(type);
        this.item.setCategory("Bakery Category: ");
    }

    @Override
    public void buildDescription(String description) {
        this.item.setDescription(description);
    }

    @Override
    public Item getItem() {
        return this.item;
    }
}
