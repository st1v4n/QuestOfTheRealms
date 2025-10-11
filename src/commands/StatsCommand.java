package commands;

import model.GameModel;
import view.GameView;

public class StatsCommand implements Command {

    @Override
    public void execute(GameModel model, GameView view) {
        model.showPlayerStats();
    }
}