package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class AttackCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        synchronized (model.map()) {
            String flag = view.requireUserInput();
            switch (flag) {
                case "up":
                    return model.attackAt(-1, 0);
                case "down":
                    return model.attackAt(1, 0);
                case "right":
                    return model.attackAt(0, 1);
                case "left":
                    return model.attackAt(0, -1);
                default:
                    return new ActionResult(Status.ERROR, "Invalid direction!");
            }
        }
    }
}
