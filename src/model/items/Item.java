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

    @Override
    public abstract String getSpecificInformation();

    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.ITEM_PICKED, this);
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.ITEM_ATTACKED, this);
    }
}
