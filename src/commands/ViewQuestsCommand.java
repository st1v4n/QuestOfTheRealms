package commands;

import backgroundActions.quests.QuestPool;
import model.GameModel;
import view.GameView;

public class ViewQuestsCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.showMessage(QuestPool.getAvailableQuestsInfo());
    }
}
