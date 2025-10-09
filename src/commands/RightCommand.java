package commands;

import model.GameModel;
import view.GameView;

public class RightCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view) {
        model.movePlayer(0, 1);
    }
}
