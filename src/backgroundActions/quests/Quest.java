package backgroundActions.quests;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import model.playerClasses.Player;

import java.util.function.Function;

public class Quest extends Thread{

    private final String description;
    private final Player player;
    private final Function<Player, Boolean> func;
    private static final int QUEST_CHECK_TIME = 1000;
    private final Notifier notifier;

    public Quest(String description, Function<Player, Boolean> func, Player player, Notifier notifier){
        this.description = description;
        this.func = func;
        this.player = player;
        this.notifier = notifier;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public void run(){
        while(true){
            if(func.apply(player)){
                player.addCompletedQuest(this);
                notifier.notify(new ActionResult(Status.SUCCESS, "Successfully completed quest: " + description));
                break;
            }
            else{
                try{
                    Thread.sleep(QUEST_CHECK_TIME);
                }
                catch(InterruptedException e){
                    notifier.notify(new ActionResult(Status.ERROR, "A problem occured while trying to complete this quest! Try activating it again!"));
                    return;
                }
            }
        }
    }
}
