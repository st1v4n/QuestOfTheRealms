package backgroundActions.quests;

import model.playerClasses.Player;
import view.GameView;

import java.util.function.Function;

public class Quest extends Thread{

    private final String description;
    private final Player player;
    private final Function<Player, Boolean> func;
    private static final int QUEST_CHECK_TIME = 10000;

    public Quest(String description, Function<Player, Boolean> func, Player player){
        this.description = description;
        this.func = func;
        this.player = player;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public void run(){
        while(true){
            if(func.apply(player)){
                player.addCompletedQuest(this);
                break;
            }
            else{
                try{
                    Thread.sleep(QUEST_CHECK_TIME);
                }
                catch(InterruptedException e){
                    run();
                }
            }
        }
    }
}
