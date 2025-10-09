package model.items;

import model.playerClasses.Player;

public class Armor extends Item{

    private final int defenseBoost;

    public Armor(int defenseBoost){
        this.defenseBoost = defenseBoost;
    }

    public int getDefenseBoost(){
        return defenseBoost;
    }

    @Override
    public void affect(Player player){
        player.addDefense(defenseBoost);
    }

    @Override
    public String toString(){
        return "Armor";
    }

    @Override
    public String getSpecificInformation(){
        return "Armor with " + defenseBoost + " defense boost";
    }

}
