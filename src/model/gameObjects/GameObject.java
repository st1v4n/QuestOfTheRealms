package model.gameObjects;

import model.actionResults.ActionResult;

public interface GameObject extends Entity{

    public abstract ActionResult sufferMovement();
    public abstract ActionResult sufferAttack(int amount);
    public abstract String getSpecificInformation();
}
