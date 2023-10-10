package org.supermarket.builder;
import org.supermarket.products.*;

public interface ICategoryBuilder {
    public void buildUniqueId(int id);
    public void buildItem(String name, double price, String type);
    public void buildSellingPriceAndQuantity(double sellingPrice, double quantity);

    public void buildDescription(String description);
    public Item getItem();
}
