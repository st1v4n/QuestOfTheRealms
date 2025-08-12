package model.enemy;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

public class Enemy implements GameObject {
    private int health;
    private int attack;

    public Enemy(int health, int attack){
        this.health = health;
        this.attack = attack;
    }

    public void takeDamage(int amount){
        health -= amount;
        if(health < 0)health = 0;
    }

    public int getAttack(){
        return attack;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth(){
        return health;
    }

    @Override
    public String toString(){
        return "Enemy";
    }

    public String getInfo(){
        return " enemy with " + health + " health remaining";
    }

    @Override
    public ActionResult collideOnMovement(Player player){
        return new ActionResult(Status.ERROR, "There is an enemy on the way!");
    }

    @Override
    public ActionResult collideOnAttack(Player player){
        try {
            player.attack(this);
        }
        catch(IllegalStateException e){
            return new ActionResult(Status.ERROR, "Not enough mana!");
        }
        if(this.isAlive()) {
            return new ActionResult(Status.SUCCESS_BUT_NO_UPDATE, "Attacked " + this.getInfo());
        }
        else{
            if(player.getFirstKilledEnemy() == null){
                player.setFirstKilledEnemy(this);
            }
            return new ActionResult(Status.SUCCESS, "Killed enemy!");
        }
    }
}
