package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class UpCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        CustomLocks.modificationLock.readLock().lock();
        try {
            model.movePlayer(-1, 0);
        } finally {
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
