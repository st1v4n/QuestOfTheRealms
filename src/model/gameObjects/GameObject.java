package model.gameObjects;

import model.actionResults.ActionResult;

public interface GameObject {

    public abstract ActionResult sufferMovement();
    public abstract ActionResult sufferAttack(int amount);
    public abstract String getInfo();
    public abstract String toString();
}
