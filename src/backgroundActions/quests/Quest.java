package backgroundActions.quests;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import model.playerClasses.Player;

import java.util.function.Function;

public class Quest extends Thread{

    /*
    Направо минаваме на Въпроси и отговори, че написах доста текст в коментари :D

    Въпроси и отговори:

    Въпрос: Защо Quest-a е нишка
    Отговор: Във всяка игра, която съм играл, винаги съм си мислел, че quest-тите са нещо, което се случва на background
    и за мен background action => thread

    Въпрос: Не може ли да стане така, че да достигнем 200 defense и да стане нощ и да ни ги махне и да не успеем да завършим quest-a, понеже има sleep(1000)?
    Отговор: Да може и като го правех знаех че може. Но истината е че никой не е казал, дори в игрите, че quest-a се complete-ва веднага като направиш дадения milestone.
    За време за окончателно завършване на дадена мисия се счита времеот от (постигане на изискването + 1 секунда) в нашата игра.
     */

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
