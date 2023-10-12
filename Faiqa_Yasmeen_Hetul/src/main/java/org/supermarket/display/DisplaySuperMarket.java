package org.supermarket.display;

import org.supermarket.CustomException;
import org.supermarket.HardCodedValue;
import org.supermarket.products.Item;
import org.supermarket.supplier.Catalog;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplaySuperMarket {
    public static boolean newLogin = true;
    private static Scanner sc = new Scanner(System.in);
    public static int display(){
        if(newLogin){
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to the F.Y.H. supermarket");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println();
            System.out.println();
            System.out.println("=================================");
            newLogin = false;
        }
        System.out.println("=============================");
        System.out.println("Super market's manager page: ");
        System.out.println("=============================");
        System.out.println("=============================");
        System.out.println("1. Sell Inventory!");
        System.out.println("2. Buy Inventory!");
        System.out.println("3. Display Information!");
        System.out.println("4. Pay Employees!");
        System.out.println("5. Logout!");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;
                System.out.println("Please enter the require option(1 to 5): ");
                ans = Integer.parseInt(sc.nextLine());

                if(ans <1 || ans>5) throw new CustomException("error1");
            }catch(CustomException ce){
                checker = true;
                ce.processError(5);

            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);

            }


        }while(checker);

        return ans;




    }




    //display all the items from the supplier catalog
    public static void displaySupplierItems(ArrayList<Item> items){
        System.out.println("UniqueId || Name         ||   SellingPrice   ||   Category   ||   Type   ||   Description   ||");
        for(Item i : items){

            System.out.println(i.getUniqueId() + "        || "+
                    i.getName() + "    ||    "+ i.getPrice() + "    ||   "+i.getCategory() + "    || "+i.getType() + "    || "+i.getDescription()+" ||");
        }
    }
    //display all the items from the list
    public static void displayItems(ArrayList<Item> items){
        System.out.println("UniqueId || Name         ||   BuyingPrice    ||   SellingPrice   ||   Quantity   ||   Category   ||   Type   ||   Description   ||");
        for(Item i : items){

            System.out.println(i.getUniqueId() + " || "+
                    i.getName() + "    || "+ i.getPrice() + "    || "+ i.getSellingPrice()+"   || "+ i.getQuantity()+"   || "+i.getCategory() + "    || "+i.getType() + "    || "+i.getDescription()+" ||");
        }
    }
    public static void displayItemSearchOption(){
        System.out.println("1. Select Item by index!");
        System.out.println("2. Select Item by name!");
    }
}
