package backgroundActions;

import locks.CustomLocks;
import model.playerClasses.Player;
import view.GameView;

public class DayUpdater extends Thread{

    private final Day day;
    private final GameView view;
    private final Player player;
    private static final int DAY_UPDATE_INTERVAL = 45000;
    private static final int PLAYER_STATS_MODIFIER = 2;

    public DayUpdater(Day day, GameView view, Player player){
        this.day = day;
        this.view = view;
        this.player = player;
    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(DAY_UPDATE_INTERVAL);
            }
            catch(InterruptedException e){
                view.showMessage("The planet stopped moving and you will be permanently stuck in either Day/Night!");
                return;
            }
            CustomLocks.modificationLock.readLock().lock();
            try {
                day.update();
                if (day.isDay()) {
                    view.showMessage("The sun has appeared, it seems that the day has started! (increasing player stats)");
                    player.increaseStats(PLAYER_STATS_MODIFIER);
                } else {
                    view.showMessage("The moon rose on the horizon and the night settled over the world! (decreasing player stats)");
                    player.decreaseStats(PLAYER_STATS_MODIFIER);
                }
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }
}
