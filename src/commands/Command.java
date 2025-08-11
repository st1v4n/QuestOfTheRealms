package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;


public interface Command {
    public abstract ActionResult execute(GameModel model, GameView view);
}
