package backgroundActions.statsIncreasers;

import locks.CustomLocks;
import model.playerClasses.Player;
import view.GameView;

public class HealthStatIncreaser extends StatIncreaser{

    private static final int HEALTH_INCREASE_AMOUNT = 100;
    private static final int HEALTH_INCREASE_INTERVAL = 10000;

    public HealthStatIncreaser(Player player, GameView view){
        super(HEALTH_INCREASE_AMOUNT, player, view);
    }

    @Override
    public void increase(){
        player.addHealth(HEALTH_INCREASE_AMOUNT);
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(HEALTH_INCREASE_INTERVAL);
            }
            catch(InterruptedException e){
                view.showMessage("Health generator failed! Try playing without health :D");
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
