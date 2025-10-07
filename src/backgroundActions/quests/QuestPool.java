package backgroundActions.quests;
import model.enemy.Boss;


import java.util.HashMap;
import java.util.Map;

public class QuestPool {

    private static Map<String, Quest> pool;
    public static final String MANA_QUEST_NAME = "mana";
    public static final String DEFENSE_QUEST_NAME = "defense";
    public static final String BOSS_FIRST_QUEST_NAME = "boss";

    static {
        pool = new HashMap<>();
        pool.put(MANA_QUEST_NAME, new Quest(QuestPool.MANA_QUEST_NAME,"reach 0 mana", (p)-> (p.getMana() == 0)));
        pool.put(DEFENSE_QUEST_NAME, new Quest(QuestPool.DEFENSE_QUEST_NAME,"reach 200 defense", (p) -> (p.getDefense() >= 200)));
        pool.put(BOSS_FIRST_QUEST_NAME, new Quest(QuestPool.BOSS_FIRST_QUEST_NAME,"kill the boss first", (p)-> (p.getFirstKilledEnemy() != null && p.getFirstKilledEnemy().getClass() == Boss.class)));
    }

    public static String getAvailableQuestsInfo(){
        StringBuilder sb = new StringBuilder();
        for(String questName : pool.keySet()){
            sb.append(questName + ": " + pool.get(questName).getDescription() + "\n");
        }
        return sb.toString();
    }

    public static Quest getQuestByName(String questName){
        return pool.get(questName);
    }
}
