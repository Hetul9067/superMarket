package org.supermarket.supplier;

import org.supermarket.builder.FreshProductBuilder;
import org.supermarket.builder.ICategoryBuilder;
import org.supermarket.products.Item;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {

    private List<Map<String, Object>> itemsData  = new ArrayList<>();

    //for searching item by name in map in constant time      || time complexity = O(1)
    private Map<String, Item> searchByName= new HashMap<>() ;

    //for searching item by index in map in constant time      || time complexity = O(1)
    private Map<Integer, Item> searchByIndex = new HashMap<>();
    private ArrayList<Item> items = new ArrayList<>(6);

    ICategoryBuilder iBuilder = new FreshProductBuilder();

    public void createItem(Map<String, Object> m, ICategoryBuilder ib){

        this.iBuilder = ib;
        double uid = Math.round((double)m.get("Column0"));
        this.iBuilder.buildUniqueId((int ) uid );
        this.iBuilder.buildItem(m.get("Column1")+"", (double) m.get("Column2"), m.get("Column4")+"");
        this.iBuilder.buildDescription(m.get("Column5")+"");
        this.items.add(this.iBuilder.getItem());
        this.searchByIndex.put(this.iBuilder.getItem().getUniqueId(), this.iBuilder.getItem());
        this.searchByName.put(this.iBuilder.getItem().getName(), this.iBuilder.getItem());




//        System.out.println(m.get("Column0"));
//        System.out.println(m.get("Column1"));
//        System.out.println(m.get("Column2"));
//        System.out.println(m.get("Column3"));
//        System.out.println(m.get("Column4"));
//        System.out.println(m.get("Column5"));
//

    }


    public int generateUID(){
        return 0;
    }


    public Item getItem(){
        return null;
    }

    public List<Map<String, Object>> getItemsData() {
        return itemsData;
    }

    public void setItemsData(List<Map<String, Object>> itemsData) {
        this.itemsData = itemsData;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ICategoryBuilder getiBuilder() {
        return iBuilder;
    }

    public void setiBuilder(ICategoryBuilder iBuilder) {
        this.iBuilder = iBuilder;
    }

    public Map<String, Item> getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(Map<String, Item> searchByName) {
        this.searchByName = searchByName;
    }

    public Map<Integer, Item> getSearchByIndex() {
        return searchByIndex;
    }

    public void setSearchByIndex(Map<Integer, Item> searchByIndex) {
        this.searchByIndex = searchByIndex;
    }
}
