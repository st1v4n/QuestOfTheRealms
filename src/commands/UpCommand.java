package commands;

import model.GameModel;
import view.GameView;

public class UpCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        model.movePlayer(-1, 0);
    }
}
