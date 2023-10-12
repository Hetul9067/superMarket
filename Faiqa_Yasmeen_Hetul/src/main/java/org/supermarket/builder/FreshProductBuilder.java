package org.supermarket.builder;

import org.supermarket.products.Item;

public class FreshProductBuilder implements ICategoryBuilder{

    private Item item;

    public FreshProductBuilder(Item item){
        this.item = item;
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
        this.item.setCategory("freshproduct");
    }

    @Override
    public void buildSellingPriceAndQuantity(double sellingPrice, double quantity){
        this.item.setSellingPrice(sellingPrice);
        this.item.setQuantity(quantity);
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
