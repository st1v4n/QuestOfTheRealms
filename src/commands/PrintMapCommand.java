package commands;

import model.GameModel;
import view.GameView;

public class PrintMapCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.showMap(model.map().asList(), model.getPlayer());
    }
}
