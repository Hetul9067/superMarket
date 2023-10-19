package org.supermarket.supermarket;

import org.supermarket.HardCodedValue;
import org.supermarket.builder.BakeryBuilder;
import org.supermarket.builder.FreshProductBuilder;
import org.supermarket.builder.GroceriesItemBuilder;
import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.factory.AbstractFactory;
import org.supermarket.products.Item;
import org.supermarket.supplier.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WareHouse {

    private ICategoryBuilder iBuilder;
    private ArrayList<Item> inventories = new ArrayList<>();
    private ArrayList<Item> itemsSold = new ArrayList<>();
    private ArrayList<Item> itemsBought = new ArrayList<>();

    private Map<Integer, Item> itemsMapById= new HashMap<>();
    private Map<String, Item> itemsMapByName= new HashMap<>();
    private double totalItemBought =0;
    private double totalItemSold =0;


    public Item createItem(Item itemSup, double quantity){

        //this.iBuilder = createBuilder(itemSup.getCategory());

        this.iBuilder = AbstractFactory.factoryMethod(itemSup.getCategory()).makeItem();
        this.iBuilder.buildUniqueId(itemSup.getUniqueId() );

        this.iBuilder.buildItem(itemSup.getName(), itemSup.getPrice(), itemSup.getType());

        this.iBuilder.buildDescription(itemSup.getDescription());

        //setting the selling price
        this.iBuilder.buildSellingPriceAndQuantity(itemSup.getPrice()*1.35, quantity);

        this.inventories.add(this.iBuilder.getItem());
        this.itemsMapById.put(this.iBuilder.getItem().getUniqueId(), this.iBuilder.getItem());
        this.itemsMapByName.put(this.iBuilder.getItem().getName(), this.iBuilder.getItem());

        //we are creating new item object and passing it to the itemsBought list
        this.createItemBought(this.iBuilder.getItem(), quantity);
        this.totalItemBought+= quantity;

        return this.iBuilder.getItem();

    }


    //find the item in inventories
    public Item findTheItem(Item itemSup){
        for(int i=0; i<this.getInventories().size();i++){
            if(this.getInventories().get(i).getName().equals(itemSup.getName())){
                return this.getInventories().get(i);
            }
        }
        return null;
    }

    //find the item in itembought list
    public void findItemInBought(String iName, double quantity){
        for(int i=0; i<this.getItemsBought().size();i++){
            if(this.getItemsBought().get(i).getName().equals(iName)){
                this.getItemsBought().get(i).setQuantityBought(this.getItemsBought().get(i).getQuantityBought()+quantity);

            }
        }

    }

    //finding the list of items which have same type
    public void findItemByType(){
        System.out.println("Please enter a item type : ");
        String ans = HardCodedValue.SCANNER.nextLine().toLowerCase();
        double totalQuantityBought = 0;
        double totalQuantitySold = 0;
        for(int i=0; i<this.itemsBought.size(); i++){
            if(this.itemsBought.get(i).getType().toLowerCase().equals(ans.toLowerCase())){
                totalQuantityBought += this.itemsBought.get(i).getQuantityBought();
            }
        }
        for(int i=0; i<this.itemsSold.size();i++){
            if(this.itemsSold.get(i).getType().toLowerCase().equals(ans.toLowerCase())){
                totalQuantitySold += this.itemsSold.get(i).getQuantitySold();
            }
        }
        System.out.println("Quantity of "+ ans + " items bought : " + totalQuantityBought);
        System.out.println("Quantity of "+ ans + " items sold : " + totalQuantitySold);

    }

    public boolean findItemInSold(String iName, double quantity){
        boolean checkItem = false;
        for(int i=0; i<this.itemsSold.size();i++){
            if(this.itemsSold.get(i).getName().equals(iName)){
                checkItem = true;
                this.itemsSold.get(i).setQuantitySold(this.itemsSold.get(i).getQuantitySold()+quantity);
            }
        }
        return checkItem;



    }
    public Item createItemSold(Item itemWareHouse, double quantity){

        this.iBuilder = AbstractFactory.factoryMethod(itemWareHouse.getCategory()).makeItem();
        this.iBuilder.buildUniqueId(itemWareHouse.getUniqueId() );

        this.iBuilder.buildItem(itemWareHouse.getName(), itemWareHouse.getPrice(), itemWareHouse.getType());

        this.iBuilder.buildDescription(itemWareHouse.getDescription());

        //setting the selling price
        this.iBuilder.buildSellingPriceAndQuantity(itemWareHouse.getPrice()*1.35, quantity);

        this.iBuilder.getItem().setQuantitySold(quantity);
        this.itemsSold.add(this.iBuilder.getItem());

        return this.iBuilder.getItem();
    }


    //building new Item Object for bought Item tracking
    public Item createItemBought(Item itemWareHouse, double quantity){

        this.iBuilder = AbstractFactory.factoryMethod(itemWareHouse.getCategory()).makeItem();
        this.iBuilder.buildUniqueId(itemWareHouse.getUniqueId() );

        this.iBuilder.buildItem(itemWareHouse.getName(), itemWareHouse.getPrice(), itemWareHouse.getType());

        this.iBuilder.buildDescription(itemWareHouse.getDescription());

        //setting the selling price
        this.iBuilder.buildSellingPriceAndQuantity(itemWareHouse.getPrice()*1.35, quantity);

        this.iBuilder.getItem().setQuantityBought(quantity);
        this.itemsBought.add(this.iBuilder.getItem());

        return this.iBuilder.getItem();
    }


    public void addItemsSold(Item item, double quantity){
        this.createItemSold(item, quantity);

    }

    //search Item by id
    public Item searchById(Map<Integer, Item> itemsMapById){

        boolean checker=true;
        int ans = 0;
        do{
            try{
                checker= false;
                System.out.println("Please enter an Item Index!");
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);;
            }
        }while(checker);
        if(itemsMapById.containsKey(ans)) {
            return itemsMapById.get(ans);
        }

        return null;
    }


    //search item by name
    public Item searchByName(Map<String, Item> itemsMapByN){
        System.out.println("Please enter an Item name!");
        String name = HardCodedValue.SCANNER.nextLine().toLowerCase();
        if(itemsMapByN.containsKey(name)){
            return itemsMapByN.get(name);
        }

        return null;
    }


    public ICategoryBuilder getiBuilder() {
        return iBuilder;
    }

    public void setiBuilder(ICategoryBuilder iBuilder) {
        this.iBuilder = iBuilder;
    }

    public ArrayList<Item> getInventories() {
        return inventories;
    }

    public void setInventories(ArrayList<Item> inventories) {
        this.inventories = inventories;
    }

    public ArrayList<Item> getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(ArrayList<Item> itemsSold) {
        this.itemsSold = itemsSold;
    }

    public ArrayList<Item> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(ArrayList<Item> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public Map<Integer, Item> getItemsMapById() {
        return itemsMapById;
    }

    public void setItemsMapById(Map<Integer, Item> itemsMapById) {
        this.itemsMapById = itemsMapById;
    }

    public Map<String, Item> getItemsMapByName() {
        return itemsMapByName;
    }

    public void setItemsMapByName(Map<String, Item> itemsMapByName) {
        this.itemsMapByName = itemsMapByName;
    }

    public double getTotalItemBought() {
        return totalItemBought;
    }

    public void setTotalItemBought(double totalItemBought) {
        this.totalItemBought = totalItemBought;
    }

    public double getTotalItemSold() {
        return totalItemSold;
    }

    public void setTotalItemSold(double totalItemSold) {
        this.totalItemSold = totalItemSold;
    }
}
