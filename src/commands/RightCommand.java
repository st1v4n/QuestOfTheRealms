package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class RightCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view) {
        CustomLocks.modificationLock.readLock().lock();
        try {
            model.movePlayer(0, 1);
        } finally{
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
