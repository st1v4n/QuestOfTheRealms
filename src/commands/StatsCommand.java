package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class StatsCommand implements Command {

    @Override
    public void execute(GameModel model, GameView view) {
        CustomLocks.modificationLock.readLock().lock();
        try {
            model.showPlayerStats();
        } finally{
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}