package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;

public class RightCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view) {
        synchronized (model.map()) {
            return model.movePlayer(0, 1);
        }
    }
}
