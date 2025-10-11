package commands;

import backgroundActions.quests.QuestPool;
import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class ViewQuestsCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        CustomLocks.modificationLock.readLock().lock();
        try {
            view.showMessage(QuestPool.getAvailableQuestsInfo());
        } finally {
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
