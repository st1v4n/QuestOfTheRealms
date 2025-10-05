package model.terrain;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

public class Blank implements GameObject {
    @Override
    public String toString(){
        return "Blank";
    }
    @Override
    public String getInfo(){ return "Just a blank space"; }
    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.SUCCESS, "Moved successfully!");
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.ERROR, "There is nothing to attack!");
    }
}
