package commands;

import model.GameModel;
import view.GameView;

public class CompletedQuestsCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.showMessage(model.getCompletedQuestsInfo());
    }
}
