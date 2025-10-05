package model.items;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

public abstract class Item implements GameObject {

    public abstract void affect(Player player);

    @Override
    public String toString(){
        return "Item";
    }

    public abstract String getInfo();

    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.SUCCESS, "You picked up an item: " + this.getInfo());
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.ERROR, "You tried to attack an item!");
    }
}
