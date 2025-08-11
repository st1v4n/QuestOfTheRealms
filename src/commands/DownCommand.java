package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;

public class DownCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        return model.movePlayer(1, 0);
    }

}
