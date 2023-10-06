package org.supermarket.supplier;

import org.supermarket.products.Item;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Catalog {

    List<Map<String, Object>> itemsData  = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>(6);

    public void createCatalog(){

        itemsData = ExcelDataReader.fetchExcelData();
        for(Map<String, Object> row : itemsData){
            System.out.println(row.get("Column0"));
            System.out.println(row.get("Column1"));
            System.out.println(row.get("Column2"));
            System.out.println(row.get("Column3"));
            System.out.println(row.get("Column4"));
            System.out.println(row.get("Column5"));

            System.out.println();
            System.out.println();
        }

    }



    public int generateUID(){
        return 0;
    }


    public Item getItem(){
        return null;
    }


}
