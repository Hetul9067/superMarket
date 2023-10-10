package org.supermarket.supermarket;

import org.supermarket.CustomException;
import org.supermarket.HardCodedValue;
import org.supermarket.display.DisplaySuperMarket;
import org.supermarket.products.Item;
import org.supermarket.supplier.Catalog;
import org.supermarket.supplier.Supplier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SuperMarket {
    private Scanner sc = new Scanner(System.in);
    private double pay = 0;
    //private ArrayList<Item> inventories = new ArrayList<>();

    private double budget = HardCodedValue.BUDGET;
    //private ArrayList<Item> itemsSold = new ArrayList<>();
//    private ArrayList<Item> itemsBought = new ArrayList<>();

//    private Map<Integer, Item> itemsMapById= new HashMap<>();
//    private Map<String, Item> itemsMapByName= new HashMap<>();
    private double itemPurchasedCost = 0;
    private double amountPaidEmployees = 0;
//    private int totalItemBought =0;
//    private int totalItemSold =0;
    private double totalExpanses = 0;
    private double totalSales = 0;
    private double totalProfit = 0;
    private Supplier supplier;

    private WareHouse wareHouse;
    public SuperMarket(Supplier sup){
        this.wareHouse = new WareHouse();
        this.supplier = sup;
    }

    public void displaySuperMarket(){
        boolean login = true;
        do{
            int ans = DisplaySuperMarket.display();
            switch(ans){
                case 1:
//                    sellInventory();
                    break;
                case 2:
                    buyInventory();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    payEmployees();
                    break;
                case 5:
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    System.out.println("Thank you!!!");
                    System.out.println("Successfully logout from the system!");
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    login = false;
                    break;

            }
        }while(login);

    }


    //for checking the user input is in specific range or not and checking the datatype
    public int userInputChecker(int range){
        boolean checker=true;
        int ans = 0;
        do{
            try{
                checker= false;
                System.out.println("please enter a value!");
                ans = Integer.parseInt(sc.nextLine());
                if(ans<1 || ans> range) throw new CustomException("error1");
            }catch(CustomException ce){
                checker = true;
                ce.processError(range);
            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);;
            }
        }while(checker);

        return ans;
    }




    //common method to check user's value is double or not
    public double userInputDoubleChecker(String msg){
        boolean checker=true;
        double ans = 0;
        do{
            try{
                checker= false;
                System.out.println(msg);
                ans = Double.parseDouble(sc.nextLine());

            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);;
            }
        }while(checker);

        return ans;
    }








    // sell the item
    public void sellInventory(){
        System.out.println();
        System.out.println();
        System.out.println("====================");
        System.out.println("Sell Item section : ");
        System.out.println("====================");
        DisplaySuperMarket.displayItems(this.wareHouse.getInventories());
        //int ans = userInputChecker(inventories.size());

        System.out.println("Do you wanna sell item by index number or name!");
        DisplaySuperMarket.displayItemSearchOption();
        int ans2 = userInputChecker(2);


        Item i1 = null;
        switch(ans2){
            case 1:
                i1 = this.wareHouse.searchById(this.wareHouse.getItemsMapById());
                break;
            case 2:
               i1 = this.wareHouse.searchByName(this.wareHouse.getItemsMapByName());
                break;
        }
        double quantity= userInputDoubleChecker("Please enter the quantity");

        if(i1.getQuantity() < quantity){
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("We don't have an enough quantity to sell!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }else{
            i1.setQuantity(i1.getQuantity()-quantity);
            this.wareHouse.setTotalItemBought(this.wareHouse.getTotalItemSold()+1);
            double sold = (i1.getQuantity()*i1.getSellingPrice());
            totalSales += sold;
            totalProfit += (sold - (i1.getQuantity()*i1.getPrice()));

            //findItemInSold();


        }


    }



    //buy the item
    public void buyInventory(){

        System.out.println("Supplier has total "+supplier.getCatalogs().size()+" no. of catalogs!");
        System.out.println("Please enter a catalog number which you wanna see!");
        int cNumber= userInputChecker(supplier.getCatalogs().size());
        Catalog c = supplier.getCatalogs().get(cNumber-1);
        DisplaySuperMarket.displayItems(c.getItems());

        System.out.println("Do you wanna buy item by index number or name!");
        DisplaySuperMarket.displayItemSearchOption();
        int ans2 = userInputChecker(2);


        Item iSup1 = null;
        switch(ans2){
            case 1:
                iSup1 = this.wareHouse.searchById(c.getSearchByIndex());
                break;
            case 2:
                iSup1 = this.wareHouse.searchByName(c.getSearchByName());
                break;
        }



        double quantity= userInputDoubleChecker("Please enter the quantity");

        Item checkedItem = this.wareHouse.findTheItem(iSup1);
        if(checkedItem== null){
            checkedItem = this.wareHouse.createItem(iSup1, quantity);

        }else{
            checkedItem.setQuantity(checkedItem.getQuantity()+quantity);
            checkedItem.setSellingPrice(checkedItem.getPrice()*1.35);
            this.wareHouse.findItemInBought(checkedItem.getName(), quantity);


        }

        this.itemPurchasedCost += (checkedItem.getPrice()* quantity);
        this.totalExpanses += (checkedItem.getPrice() * quantity);
        this.budget -= (checkedItem.getPrice() * quantity);








    }





    //display the information regarding to the supermarket
    public void display(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        System.out.println();
        System.out.println();
        System.out.println("=========================================");
        System.out.println("Current Date and Time : " + formattedTime);
        System.out.println("Total number of items bought : " + this.wareHouse.getTotalItemBought());
        System.out.println("Total number of items sold : "+ this.wareHouse.getTotalItemSold());
        System.out.println("Total cost of all items purchased : "+ this.itemPurchasedCost);
        System.out.println("Total amount paid to employees : " +  this.amountPaidEmployees);
        System.out.println("Total expenses : "+ this.totalExpanses);
        System.out.println("Total sales : "+ this.totalSales);
        System.out.println("Total profit : "+ this.totalProfit);
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println("1. See the Quantity of the Item type sold and bought!");
        System.out.println("2. Go to the main page!");
        int ans = userInputChecker(2);

        if(ans == 1){

            this.wareHouse.findItemByType();
        }





    }
    public void payEmployees(){

    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

//    public ArrayList<Item> getInventories() {
//        return inventories;
//    }
//
//    public void setInventories(ArrayList<Item> inventories) {
//        this.inventories = inventories;
//    }

    public double getItemPurchasedCost() {
        return itemPurchasedCost;
    }

    public void setItemPurchasedCost(double itemPurchasedCost) {
        this.itemPurchasedCost = itemPurchasedCost;
    }

    public double getAmountPaidEmployees() {
        return amountPaidEmployees;
    }

    public void setAmountPaidEmployees(double amountPaidEmployees) {
        this.amountPaidEmployees = amountPaidEmployees;
    }

//    public int getTotalItemBought() {
//        return totalItemBought;
//    }
//
//    public void setTotalItemBought(int totalItemBought) {
//        this.totalItemBought = totalItemBought;
//    }
//
//    public int getTotalItemSold() {
//        return totalItemSold;
//    }
//
//    public void setTotalItemSold(int totalItemSold) {
//        this.totalItemSold = totalItemSold;
//    }

    public double getTotalExpanses() {
        return totalExpanses;
    }

    public void setTotalExpanses(double totalExpanses) {
        this.totalExpanses = totalExpanses;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
