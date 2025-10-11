package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class PrintMapCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        CustomLocks.modificationLock.readLock().lock();
        try {
            view.showMap(model.getMapCopy(), model.getPlayerRow(), model.getPlayerCol());
        } finally {
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
