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
    public ActionResult collideOnMovement(Player player){
        return new ActionResult(Status.ERROR, "There is an obstacle on the way!");
    }
    @Override
    public ActionResult collideOnAttack(Player player){
        return new ActionResult(Status.ERROR, "You tried to attack the wall!");
    }
}
