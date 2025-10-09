package commands;

import model.GameModel;
import view.GameView;

public class StartQuestCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String questName = view.requireUserInput();
        model.startQuest(questName);
    }
}
