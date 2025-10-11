package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class StartQuestCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String questName = view.requireUserInput();
        CustomLocks.modificationLock.readLock().lock();
        try {
            model.startQuest(questName);
        } finally {
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
