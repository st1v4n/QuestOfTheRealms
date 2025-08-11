package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;

public class AttackRightCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        return model.attackAt(0, 1);
    }
}
