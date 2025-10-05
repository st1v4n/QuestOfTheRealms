package model.terrain;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

public class Wall implements GameObject {
    @Override
    public String toString(){
        return "Wall";
    }
    @Override
    public String getInfo(){ return "Just a random wall"; }
    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.ERROR, "There is an obstacle on the way!");
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.ERROR, "You tried to attack the wall!");
    }
}
