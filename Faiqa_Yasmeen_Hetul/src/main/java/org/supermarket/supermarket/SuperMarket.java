package org.supermarket.supermarket;

import org.supermarket.CustomException;
import org.supermarket.HardCodedValue;
import org.supermarket.display.DisplaySuperMarket;
import org.supermarket.products.Item;
import org.supermarket.supplier.Catalog;
import org.supermarket.supplier.Supplier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SuperMarket {

    private double pay = 0;


    private double budget = HardCodedValue.BUDGET;

    private double itemPurchasedCost = 0;
    private double amountPaidEmployees = 0;

    private double totalExpanses = 0;
    private double totalSales = 0;
    private double totalProfit = 0;
    private Supplier supplier;
    private ArrayList<Employee> employees = new ArrayList<>();

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
                    sellInventory();
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
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());
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
                ans = Double.parseDouble(HardCodedValue.SCANNER.nextLine());

            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);;
            }
        }while(checker);

        return ans;
    }







    //Checking the item is available or not in supplier's catalog
    public Item superMarketItemChecker(int ans2){
        Item i1 = null;
        do{
            switch(ans2){
                case 1:
                    i1 = this.wareHouse.searchById(this.wareHouse.getItemsMapById());
                    break;
                case 2:
                    i1 = this.wareHouse.searchByName(this.wareHouse.getItemsMapByName());
                    break;
            }

            if(i1==null){
                System.out.println("Sorry, we don't have that item");
                System.out.println("do you wanna sell another item?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int inp = userInputChecker(2);
                if(inp == 2) {
                    System.out.println("Now we are going out from the selling section!");
                    return null;
                }
            }
        }while(i1==null);

        return i1;
    }


    // sell the item
    public void sellInventory(){
        System.out.println();
        System.out.println();
        System.out.println("====================");
        System.out.println("Sell Item section : ");
        System.out.println("====================");
        if(this.wareHouse.getInventories().isEmpty()){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Sorry!! we don't have any item to sell");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Returning to the main menu");
            return;

        }

        DisplaySuperMarket.displayItems(this.wareHouse.getInventories());
        //int ans = userInputChecker(inventories.size());

        System.out.println("Do you wanna sell item by index number or name!");
        DisplaySuperMarket.displayItemSearchOption();
        int ans2 = userInputChecker(2);


        //for checking the item in supermarket warehouse
        Item i1 = superMarketItemChecker(ans2);
        if(i1 == null) return;

        double quantity= userInputDoubleChecker("Please enter the quantity");

        if(i1.getQuantity() < quantity){
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("We don't have an enough quantity to sell!");

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            return;
        }else{
            i1.setQuantity(i1.getQuantity()-quantity);
            this.wareHouse.setTotalItemSold(this.wareHouse.getTotalItemSold()+quantity);
            double sold = (quantity*i1.getSellingPrice());
            totalSales += sold;
            totalProfit += (sold - (quantity*i1.getPrice()));
            budget += sold;

            boolean itemInSoldChecker = this.wareHouse.findItemInSold(i1.getName(), quantity);
            if(!itemInSoldChecker){

                this.wareHouse.addItemsSold(i1, quantity);
            }


        }

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("We have successfully sold a item("+ i1.getName()+") of quantity : "+ quantity);
        System.out.println("-----------------------------------------------------------------------------");

    }


    //Checking the item is available or not in supplier's catalog
    public Item supplierItemChecker(int ans2, Catalog c){
        Item iSup1 = null;
        do{
            switch(ans2){
                case 1:
                    iSup1 = this.wareHouse.searchById(c.getSearchByIndex());
                    break;
                case 2:
                    iSup1 = this.wareHouse.searchByName(c.getSearchByName());
                    break;
            }
            if(iSup1==null){
                System.out.println("Sorry, we don't have that item");
                System.out.println("do you wanna buy another item?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int inp = userInputChecker(2);
                if(inp == 2) {
                    System.out.println("Now we are going out from the buying section!");
                    return null;
                }
            }
        }while(iSup1==null);

        return iSup1;
    }

    //buy the item
    public void buyInventory(){

        System.out.println("Supplier has total "+supplier.getCatalogs().size()+" no. of catalogs!");
        System.out.println("Please enter a catalog number which you wanna see!");
        int cNumber= userInputChecker(supplier.getCatalogs().size());
        Catalog c = supplier.getCatalogs().get(cNumber-1);
        DisplaySuperMarket.displaySupplierItems(c.getItems());

        System.out.println("Do you wanna buy item by index number or name!");
        DisplaySuperMarket.displayItemSearchOption();
        int ans2 = userInputChecker(2);

        //Checking the item is available or not in supplier's catalog
        Item iSup1 = supplierItemChecker(ans2, c);



        if(iSup1== null) return;

        double quantity  = 0;

        boolean fundChecker = false;
        do{
            fundChecker = false;
            quantity= userInputDoubleChecker("Please enter the quantity");
            if(this.budget < (quantity* iSup1.getPrice())){

                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("ough, insufficient fund!!");
                System.out.println("you should check your pocket before buying anything buddy!");
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println();
                System.out.println();

                System.out.println("Do you wanna reenter the quantity???");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int input = userInputChecker(2);
                fundChecker = (input == 1) ;

                if(input == 2) {
                    System.out.println("Now we are going out from the buying section!");
                    return;
                }


            }


        }while(fundChecker);




        Item checkedItem = this.wareHouse.findTheItem(iSup1);
        if(checkedItem== null){
            checkedItem = this.wareHouse.createItem(iSup1, quantity);

        }else{
            checkedItem.setQuantity(checkedItem.getQuantity()+quantity);
            checkedItem.setSellingPrice(checkedItem.getPrice()*1.35);
            this.wareHouse.findItemInBought(checkedItem.getName(), quantity);
            this.wareHouse.setTotalItemBought(this.wareHouse.getTotalItemBought()+quantity);

        }

        this.itemPurchasedCost += (checkedItem.getPrice()* quantity);
        this.totalExpanses += (checkedItem.getPrice() * quantity);
        this.budget -= (checkedItem.getPrice() * quantity);


        System.out.println("#####################################################################################");
        System.out.println("We have successfully bought a item("+ iSup1.getName()+") of "+ quantity+ " quantity!");
        System.out.println("#####################################################################################");



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
        System.out.println("Supermarket's budget : "+ this.budget);
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
        LocalDateTime ld = this.employees.get(0).getLastPayTime();
        LocalDateTime currentDateTime = LocalDateTime.now();
        long minutes = Duration.between(ld, currentDateTime).toMinutes();

        if(minutes>0){

            System.out.println("minutes worked : "+ minutes);
            double salary = minutes * 2600;

            for(Employee e : employees){
                e.setLastPayTime(currentDateTime);
                e.setSalary(e.getSalary()+salary);
                this.totalExpanses += salary;
                this.budget -= salary;
                this.amountPaidEmployees += salary;
            }

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Salary has been paid to the employees successfully!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println();
            System.out.println();
        }else{
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("Employees have been paid already!");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
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


    public void addEmployee(Employee e){
        this.employees.add(e);
    }

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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }
}
