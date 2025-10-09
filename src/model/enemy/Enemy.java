package model.enemy;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;

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

    @Override
    public String getSpecificInformation(){
        return " enemy with " + health + " health remaining";
    }

    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.STEPPED_ON_ENEMY, this);
    }

    @Override
    public ActionResult sufferAttack(int amount){
        this.takeDamage(amount);
        if(this.isAlive()) {
            return new ActionResult(Status.ATTACKED_ENEMY, this);
        }
        else{
            return new ActionResult(Status.KILLED_ENEMY, this);
        }
    }
}
