package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class StatsCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        return new ActionResult(Status.SUCCESS,
                "Health: " + model.getPlayerHealth() + "\n" +
                          "Mana: " + model.getPlayerMana() + "\n" +
                          "Attack: " + model.getPlayerAttack() + "\n" +
                          "Defense: " + model.getPlayerDefense() + "\n" +
                          "Inventory: " + model.getPlayerInventoryContent() + "\n");
    }
}
