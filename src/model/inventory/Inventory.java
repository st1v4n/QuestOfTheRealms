package model.inventory;

import model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        if(!items.remove(item)){
            throw new IllegalStateException("You down own this item!");
        }
    }

    public Item getItemAt(int index){
        if(index >= items.size() || index < 0)return null;
        return items.get(index);
    }

    @Override
    public String toString(){
        if(items.isEmpty())return "Inventory is Empty!";
        String result = "";
        for(int i=0;i<items.size();++i){
            result += "index " + i + ": " + items.get(i).toString() + "\n";
        }
        return result;
    }

}
