package commands;

import model.GameModel;
import view.GameView;

public class RightCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String result = model.movePlayer(0,1);
        view.promptMessage(result);
    }
}
