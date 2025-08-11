package model.gameObjects;

import model.actionResults.ActionResult;
import model.playerClasses.Player;

public interface GameObject {
    public abstract ActionResult collideOnMovement(Player player);
    public abstract ActionResult collideOnAttack(Player player);
}
