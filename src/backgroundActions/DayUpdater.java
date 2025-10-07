package backgroundActions;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import model.playerClasses.Player;

public class DayUpdater extends Thread{

    private final Day day;
    private final Notifier notifier;
    private final Player player;
    private static final int DAY_UPDATE_INTERVAL = 45000;
    private static final int PLAYER_STATS_MODIFIER = 2;

    public DayUpdater(Day day, Notifier notifier, Player player){
        this.day = day;
        this.notifier = notifier;
        this.player = player;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(DAY_UPDATE_INTERVAL);
                day.update();
                if(day.isDay()){
                    synchronized (player) {
                    notifier.notify(new ActionResult(Status.SUCCESS, "The sun has appeared, it seems that the day has started! (increasing player stats)"));
                        player.increaseStats(PLAYER_STATS_MODIFIER);
                    }
                }
                else{
                    synchronized (player) {
                    notifier.notify(new ActionResult(Status.SUCCESS, "The moon rose on the horizon and the night settled over the world! (decreasing player stats)"));
                        player.decreaseStats(PLAYER_STATS_MODIFIER);
                    }
                }
            }
            catch(InterruptedException e){
                notifier.notify(new ActionResult(Status.ERROR, "The planet stopped moving and you will be permanently stuck in either Day/Night!"));
                return;
            }
        }
    }
}
