package org.supermarket.builder;

//import org.supermarket.*;
import org.supermarket.products.Item;
//import main.java.products.Item;

public class GroceriesItemBuilder implements ICategoryBuilder{

    private Item item;

    public GroceriesItemBuilder(){
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
        this.item.setCategory("Groceries Category: ");
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
