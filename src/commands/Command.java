package commands;

import model.GameModel;
import view.GameView;

public interface Command {
    public abstract void execute(GameModel model, GameView view);
}
