package backgroundActions.statsIncreasers;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

public abstract class StatIncreaser extends Thread{

    protected final int interval;
    protected final GameView view;
    protected final GameModel model;

    public StatIncreaser(int interval, GameModel model, GameView view){
        this.interval = interval;
        this.model = model;
        this.view = view;
    }

    protected abstract void increase();

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(interval);
            }
            catch(InterruptedException e){
                view.showMessage("generator failed! Switching to hardcore mode");
            }
            CustomLocks.modificationLock.readLock().lock();
            try {
                increase();
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }

}
