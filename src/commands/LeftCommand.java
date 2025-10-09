package commands;

import model.GameModel;
import view.GameView;

public class LeftCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        model.movePlayer(0, -1);
    }
}
