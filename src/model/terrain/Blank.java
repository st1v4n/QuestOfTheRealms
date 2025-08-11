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
    public ActionResult collideOnMovement(Player player){
        return new ActionResult(Status.SUCCESS, "Moved successfully!");
    }
    @Override
    public ActionResult collideOnAttack(Player player){
        return new ActionResult(Status.ERROR, "There is nothing to attack!");
    }
}
