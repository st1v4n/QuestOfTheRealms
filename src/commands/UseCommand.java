package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class UseCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String flag = view.requireUserInput();
        CustomLocks.modificationLock.readLock().lock();
        try{
            int index = Integer.parseInt(flag);
            model.useItemAt(index);
        }
        catch(Exception e){
            view.showMessage("You have entered invalid index");
        } finally {
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
