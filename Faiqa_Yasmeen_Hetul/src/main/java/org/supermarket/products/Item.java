package org.supermarket.products;

public class Item {
    private int uniqueId=0;
    private String name="";
    private double price=0;
    private double sellingPrice = 0;
    private double quantity=0;
    private double quantitySold = 0;
    private double quantityBought = 0;
    private  String category="";

    private String description;
    private String type;

    public Item(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(double quantityBought) {
        this.quantityBought = quantityBought;
    }
}
