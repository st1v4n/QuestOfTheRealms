package backgroundActions.quests;

import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.enemy.Boss;
import model.notifiers.Notifier;
import model.playerClasses.Player;

import java.util.HashMap;
import java.util.Map;

public class QuestPool {

    private final Map<Integer, Quest> pool;

    public QuestPool(Player player, Notifier notifier){
        pool = new HashMap<>();
        pool.put(0, new Quest("reach 0 mana", (p)-> (p.getMana() == 0), player, notifier));
        pool.put(1, new Quest("reach 200 defense", (p) -> (p.getDefense() == 200), player, notifier));
        pool.put(2, new Quest("kill the boss first", (p)-> (p.getFirstKilledEnemy() != null && p.getFirstKilledEnemy().getClass() == Boss.class), player, notifier));
    }

    public String getAvailableQuestsInfo(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<pool.size();++i){
            sb.append("index " + i + ": " + pool.get(i).getDescription() + "\n");
        }
        return sb.toString();
    }

    public ActionResult startQuest(int index){
        if(index < 0 || index >= pool.size())return new ActionResult(Status.ERROR, "Invalid quest index!");
        pool.get(index).start();
        return new ActionResult(Status.SUCCESS, "Successfully started quest!");
    }

    public Quest getQuestAtIndex(int index){
        return pool.get(index);
    }
}
