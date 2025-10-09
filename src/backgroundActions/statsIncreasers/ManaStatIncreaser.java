package backgroundActions.statsIncreasers;

import locks.CustomLocks;
import model.playerClasses.Player;
import view.GameView;

public class ManaStatIncreaser extends StatIncreaser{

    private static final int MANA_INCREASE_AMOUNT = 10;
    private static final int MANA_INCREASE_INTERVAL = 8000;

    public ManaStatIncreaser(Player player, GameView view){
        super(MANA_INCREASE_AMOUNT, player, view);
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
            }
            catch(InterruptedException e){
                view.showMessage("Mana generator failed! Try playing without mana :D");
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
