package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class CompletedQuestsCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        CustomLocks.modificationLock.readLock().lock();
        try {
            view.showMessage(model.getCompletedQuestsInfo());
        } finally{
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
