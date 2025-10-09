package backgroundActions.statsIncreasers;

import locks.CustomLocks;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import model.playerClasses.Player;

public class HealthStatIncreaser extends StatIncreaser{

    private static final int HEALTH_INCREASE_AMOUNT = 100;
    private static final int HEALTH_INCREASE_INTERVAL = 10000;

    public HealthStatIncreaser(Player player, Notifier notifier){
        super(HEALTH_INCREASE_AMOUNT, player, notifier);
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
                notifier.notify(new ActionResult(Status.ERROR, "Health generator failed! Try playing without mana :D"));
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
