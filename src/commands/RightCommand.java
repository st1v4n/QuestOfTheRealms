package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;

public class RightCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view) {
        return model.movePlayer(0, 1);
    }
}
