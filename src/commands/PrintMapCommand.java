package commands;

import model.GameModel;
import view.GameView;

public class PrintMapCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.visualiseMap(model.getMap(), model.getMapRows(), model.getMapColumns());
    }
}
