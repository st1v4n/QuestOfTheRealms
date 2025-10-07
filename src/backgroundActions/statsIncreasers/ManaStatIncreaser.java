package backgroundActions.statsIncreasers;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import model.playerClasses.Player;

public class ManaStatIncreaser extends StatIncreaser{

    private static final int MANA_INCREASE_AMOUNT = 10;
    private static final int MANA_INCREASE_INTERVAL = 8000;

    public ManaStatIncreaser(Player player, Notifier notifier){
        super(MANA_INCREASE_AMOUNT, player, notifier);
    }

    @Override
    public void increase(){
        player.addMana(MANA_INCREASE_AMOUNT);
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(MANA_INCREASE_INTERVAL);
                synchronized (player) {
                    increase();
                }
            }
            catch(InterruptedException e){
                notifier.notify(new ActionResult(Status.ERROR, "Mana generator failed! Try playing without mana :D"));
            }
        }
    }
}
