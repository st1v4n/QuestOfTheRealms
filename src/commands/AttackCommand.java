package commands;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public class AttackCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String flag = view.requireUserInput();
        CustomLocks.modificationLock.readLock().lock();
        try {
            switch (flag) {
                case "up":
                    model.attackAt(-1, 0);
                    break;
                case "down":
                    model.attackAt(1, 0);
                    break;
                case "right":
                    model.attackAt(0, 1);
                    break;
                case "left":
                    model.attackAt(0, -1);
                    break;
                default:
                    view.showMessage("invalid direction");
            }
        } finally{
            CustomLocks.modificationLock.readLock().unlock();
        }
    }
}
