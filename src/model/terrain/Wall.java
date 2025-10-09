package model.terrain;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;

public class Wall implements GameObject {
    @Override
    public String toString(){
        return "Wall";
    }
    @Override
    public String getSpecificInformation(){ return ""; }
    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.HIT_A_WALL, null);
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.WALL_ATTACKED, null);
    }
}
