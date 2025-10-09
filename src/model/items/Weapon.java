package model.items;

import model.playerClasses.Player;


public class Weapon extends Item{

    private final int attackBoost;

    public Weapon(int attackBoost){
        this.attackBoost = attackBoost;
    }

    public int getAttackBoost(){
        return attackBoost;
    }

    @Override
    public void affect(Player player){
        player.addAttack(attackBoost);
    }

    @Override
    public String toString(){
        return "Weapon";
    }

    @Override
    public String getSpecificInformation(){
        return "Weapon with " + attackBoost + " attack boost";
    }

}
