package model.terrain;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;

public class Blank implements GameObject {
    @Override
    public String toString(){
        return "Blank";
    }
    @Override
    public String getSpecificInformation(){ return ""; }
    @Override
    public ActionResult sufferMovement(){
        return new ActionResult(Status.MOVED, null);
    }
    @Override
    public ActionResult sufferAttack(int amount){
        return new ActionResult(Status.BLANK_ATTACKED, null);
    }
}
