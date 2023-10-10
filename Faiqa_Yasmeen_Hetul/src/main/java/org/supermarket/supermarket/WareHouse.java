package org.supermarket.supermarket;

import org.supermarket.HardCodedValue;
import org.supermarket.builder.BakeryBuilder;
import org.supermarket.builder.FreshProductBuilder;
import org.supermarket.builder.GroceriesItemBuilder;
import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.products.Item;
import org.supermarket.supplier.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WareHouse {
    private Scanner sc = new Scanner(System.in);
    private ICategoryBuilder iBuilder = new FreshProductBuilder();
    private ArrayList<Item> inventories = new ArrayList<>();
    private ArrayList<Item> itemsSold = new ArrayList<>();
    private ArrayList<Item> itemsBought = new ArrayList<>();

    private Map<Integer, Item> itemsMapById= new HashMap<>();
    private Map<String, Item> itemsMapByName= new HashMap<>();
    private int totalItemBought =0;
    private int totalItemSold =0;


    public Item createItem(Item itemSup, double quantity){
        this.iBuilder = createBuilder(itemSup.getCategory());

        this.iBuilder.buildUniqueId(itemSup.getUniqueId() );

        this.iBuilder.buildItem(itemSup.getName(), itemSup.getPrice(), itemSup.getType());

        this.iBuilder.buildDescription(itemSup.getDescription());

        //setting the selling price
        this.iBuilder.buildSellingPriceAndQuantity(itemSup.getPrice()*1.35, quantity);

        this.inventories.add(this.iBuilder.getItem());
        this.itemsMapById.put(this.iBuilder.getItem().getUniqueId(), this.iBuilder.getItem());
        this.itemsMapByName.put(this.iBuilder.getItem().getName(), this.iBuilder.getItem());
        this.itemsBought.add(this.iBuilder.getItem());
        this.totalItemBought++;

        return this.iBuilder.getItem();

    }
    public ICategoryBuilder createBuilder(String category){
        if(category.toLowerCase().equals("freshproduct category: ")){
            return new FreshProductBuilder();
        }else if(category.toLowerCase().equals("Bakery Category: ".toLowerCase())){
            return new BakeryBuilder();
        }else if(category.toLowerCase().equals("Groceries Category: ".toLowerCase())){
            return new GroceriesItemBuilder();
        }
        return null;
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
            if(this.getItemsBought().get(i).equals(iName)){
                this.getItemsBought().get(i).setQuantityBought(this.getItemsBought().get(i).getQuantityBought()+quantity);
            }
        }

    }

    //finding the list of items which have same type
    public void findItemByType(){
        System.out.println("Please enter a item type : ");
        String ans = sc.nextLine();
        double totalQuantityBought = 0;
        double totalQuantitySold = 0;
        for(int i=0; i<this.itemsBought.size(); i++){
            if(this.itemsBought.get(i).getType().toLowerCase().equals(ans.toLowerCase())){
                totalQuantityBought += this.itemsBought.get(i).getQuantity();
            }
        }
        for(int i=0; i<this.itemsSold.size();i++){
            if(this.itemsSold.get(i).getType().toLowerCase().equals(ans.toLowerCase())){
                totalQuantitySold += this.itemsSold.get(i).getQuantity();
            }
        }
        System.out.println("Quantity of "+ ans + " items bought : " + totalQuantityBought);
        System.out.println("Quantity of "+ ans + " items sold : " + totalQuantitySold);

    }

    public void findItemInSold(String iName, double quantity){
        boolean checkItem = false;
        for(int i=0; i<this.itemsSold.size();i++){
            if(this.itemsSold.get(i).equals(iName)){
                checkItem = true;
                this.itemsSold.get(i).setQuantitySold(this.itemsSold.get(i).getQuantitySold()+quantity);
            }
        }


    }

    //search Item by id
    public Item searchById(Map<Integer, Item> itemsMapById){

        boolean checker=true;
        int ans = 0;
        do{
            try{
                checker= false;
                System.out.println("Please enter an Item Index!");
                ans = Integer.parseInt(sc.nextLine());

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
        String name = sc.nextLine().toLowerCase();
        if(itemsMapByN.containsKey(name)){
            return itemsMapByN.get(name);
        }

        return null;
    }
    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
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

    public int getTotalItemBought() {
        return totalItemBought;
    }

    public void setTotalItemBought(int totalItemBought) {
        this.totalItemBought = totalItemBought;
    }

    public int getTotalItemSold() {
        return totalItemSold;
    }

    public void setTotalItemSold(int totalItemSold) {
        this.totalItemSold = totalItemSold;
    }
}
