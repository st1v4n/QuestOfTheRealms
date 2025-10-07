package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class StatsCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view) {
        synchronized (model.getPlayer()) {
            return new ActionResult(Status.SUCCESS,
                    "Health: " + model.getPlayer().getHealth() + "\n" +
                            "Mana: " + model.getPlayer().getMana() + "\n" +
                            "Attack: " + model.getPlayer().getAttack() + "\n" +
                            "Defense: " + model.getPlayer().getDefense() + "\n" +
                            "Inventory: " + model.getPlayerInventoryContent() + "\n");
        }
    }
}
