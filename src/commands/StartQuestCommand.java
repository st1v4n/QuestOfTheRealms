package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class StartQuestCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        String questName = view.requireUserInput();
        return model.startQuest(questName);
    }
}
