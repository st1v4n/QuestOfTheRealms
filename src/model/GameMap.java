package model;

import backgroundActions.quests.Quest;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.enemy.Enemy;
import model.items.Item;
import model.notifiers.Notifier;
import model.playerClasses.Player;
import model.terrain.Blank;

import java.util.*;

public class GameMap {

    /*
    Картата е само карта и нейната отговорност е да знае къде какво има
     */

    private List<List<GameObject>> map;
    private final Player player;

    public GameMap(List<List<GameObject>> map, Player player){
        this.map = map;
        this.player = player;
    }

    public int getYBorder(){
        return map.size();
    }

    public int getXBorder(){
        return map.getFirst().size();
    }

    public List<List<GameObject>> asList(){
        return new ArrayList<>(map);
    }

    public boolean isBlankPlace(int row, int col){
        return map.get(row).get(col).getClass().equals(Blank.class);
    }

    public ActionResult addEnemyAt(Enemy enemy, int row, int col){
        if(!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())){
            return new ActionResult(Status.ERROR, "Tried to spawn enemy on top of a game object...(mission failed successfully)");
        }
        map.get(row).set(col, enemy);
        return new ActionResult(Status.SUCCESS, "Successfully added enemy!");
    }

    public ActionResult addItemAt(Item item, int row, int col){
        if(!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())){
            return new ActionResult(Status.ERROR, "Tried to spawn item on top of a game object...(mission failed successfully)");
        }
        map.get(row).set(col, item);
        return new ActionResult(Status.SUCCESS, "Successfully added item!");
    }

    // функциите move и attack са разделени!
    public ActionResult movePlayer(int rowAddition, int colAddition){
        synchronized (player) {
            int potentialRow = player.getRow() + rowAddition;
            int potentialCol = player.getColumn() + colAddition;
            GameObject objectAtPosition = map.get(potentialRow).get(potentialCol);
            ActionResult action = objectAtPosition.sufferMovement();
            if (action.didSucceed()) {
                try {
                    player.addItemToInventory((Item) objectAtPosition);
                } catch (Exception e) {
                } // ако не е било предмет не прави нищо
                map.get(potentialRow).set(potentialCol, new Blank());
                player.move(potentialRow, potentialCol);
            }
            return action;
        }
    }

    // функциите move и attack са разделени!
    public ActionResult attackAt(int rowAddition, int colAddition){
        synchronized (player) {
            int potentialRow = player.getRow() + rowAddition;
            int potentialCol = player.getColumn() + colAddition;
            ActionResult action = player.attack(map.get(potentialRow).get(potentialCol));
            if (action.didSucceed()) {
                map.get(potentialRow).set(potentialCol, new Blank());
            }
            return action;
        }
    }

    public ActionResult useItemAt(int index){
        synchronized (player) {
            try {
                player.useItem(index);
            } catch (IllegalStateException illegalExc) {
                return new ActionResult(Status.ERROR, "You do not have this item!");
            } catch (ArrayIndexOutOfBoundsException indexOutExc) {
                return new ActionResult(Status.ERROR, "Invalid item index!");
            }
            return new ActionResult(Status.SUCCESS, "Successfully used the item!");
        }
    }

    public ActionResult getCompletedQuests(){
        synchronized (player) {
            StringBuilder sb = new StringBuilder();
            Set<String> completedQuests = player.getCompletedQuests();
            for (String questName : completedQuests) {
                sb.append(questName + "\n");
            }
            return new ActionResult(Status.SUCCESS, sb.toString());
        }
    }

    public String getPlayerInventoryContent(){
        synchronized (player) {
            return player.getInventoryContent();
        }
    }

    public void setEventNotifier(Notifier notifier){
        player.setNotifier(notifier);
    }

    public ActionResult startQuest(Quest quest){
        synchronized (player) {
            return player.startQuest(quest);
        }
    }

    public Player getPlayer(){
        return player;
    }
}
