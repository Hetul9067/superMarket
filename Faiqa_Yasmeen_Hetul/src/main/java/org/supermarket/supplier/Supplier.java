package org.supermarket.supplier;

import org.supermarket.builder.BakeryBuilder;
import org.supermarket.builder.FreshProductBuilder;
import org.supermarket.builder.GroceriesItemBuilder;
import org.supermarket.builder.ICategoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Supplier {


    private ArrayList<Catalog> catalogs = new ArrayList<>();
    private List<Map<String, Object>> itemsData  = new ArrayList<>();

    private ICategoryBuilder iBuilder ;
    public ArrayList<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(ArrayList<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public List<Map<String, Object>> getItemsData() {
        return itemsData;
    }

    public void setItemsData(List<Map<String, Object>> itemsData) {
        this.itemsData = itemsData;
    }

    public ICategoryBuilder getiBuilder() {
        return iBuilder;
    }

    public void setiBuilder(ICategoryBuilder iBuilder) {
        this.iBuilder = iBuilder;
    }



    public ICategoryBuilder createBuilder(String category){
        if(category.toLowerCase().equals("freshproduct")){
            return new FreshProductBuilder();
        }else if(category.toLowerCase().equals("bakery")){
            return new BakeryBuilder();
        }else if(category.toLowerCase().equals("grocery")){
            return new GroceriesItemBuilder();
        }
        return null;
    }
    public void generateCatalogs(){
        itemsData = ExcelDataReader.fetchExcelData();


        Catalog c1 = new Catalog();
        int count = 0;
        catalogs.add(c1);
        for(Map<String, Object> row : itemsData){

            if(count==6){
                    count =0;
                    c1 = new Catalog();
                    catalogs.add(c1);

            }
            ICategoryBuilder i1 = createBuilder(row.get("Column3")+"");
            c1.createItem(row, i1);
            count++;

        }




    }


}
