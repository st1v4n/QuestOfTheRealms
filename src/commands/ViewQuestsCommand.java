package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class ViewQuestsCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        view.showMessage(model.getAvailableQuests());
        return new ActionResult(Status.SUCCESS, "Printed all available quests!");
    }
}
