package model.items;

import model.playerClasses.Player;

public class Potion extends Item{

    private final int healthBoost;
    private final int manaBoost;

    public Potion(int healthBoost, int manaBoost){
        this.healthBoost = healthBoost;
        this.manaBoost = manaBoost;
    }

    public int getHealthBoost(){
        return healthBoost;
    }

    public int getManaBoost(){
        return manaBoost;
    }

    @Override
    public void affect(Player player){
        player.addHealth(healthBoost);
        player.addMana(manaBoost);
    }

    @Override
    public String toString(){
        return "Potion";
    }

    public String getInfo(){
        return "Potion with " + healthBoost + " health boost and " + manaBoost + " mana boost";
    }
}
