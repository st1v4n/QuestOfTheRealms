package model;

import backgroundActions.quests.Quest;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.enemy.Enemy;
import model.items.Item;
import model.notifiers.Notifier;
import model.playerClasses.Player;
import model.playerClasses.PlayerClass;
import model.terrain.Blank;

import java.util.*;

public class GameMap {

    /*
    Картата е само карта и нейната отговорност е да знае къде какво има
     */

    private List<List<GameObject>> map;
    private Player player;
    private transient Notifier notifier;

    public GameMap(List<List<GameObject>> map, Player player){
        this.map = map;
        this.player = player;
    }

    public synchronized int getYBorder(){
        return map.size();
    }

    public synchronized int getXBorder(){
        return map.getFirst().size();
    }

    public synchronized List<List<GameObject>> asList(){ // правим deep copy, за да се избегнат проблеми
        List<List<GameObject>> result = new ArrayList<>();
        for(int i=0;i<map.size();++i){
            result.add(new ArrayList<>(map.get(i)));
        }
        return result;
    }

    public synchronized boolean isBlankPlace(int row, int col){
        return map.get(row).get(col).getClass().equals(Blank.class);
    }

    public synchronized void addEnemyAt(Enemy enemy, int row, int col) {
        if (!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())) {
            notifier.notify(new ActionResult(Status.WRONG_SPAWN, enemy));
        }
        map.get(row).set(col, enemy);
        notifier.notify(new ActionResult(Status.SPAWNED_ENEMY, enemy));
    }

    public synchronized void addItemAt(Item item, int row, int col) {
        if (!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())) {
            notifier.notify(new ActionResult(Status.WRONG_SPAWN, item));
        }
        map.get(row).set(col, item);
        notifier.notify(new ActionResult(Status.SPAWNED_ITEM, item));
    }

    // функциите move и attack са разделени!
    public synchronized void movePlayer(int rowAddition, int colAddition) {
        int potentialRow = player.getRow() + rowAddition;
        int potentialCol = player.getColumn() + colAddition;
        GameObject objectAtPosition = map.get(potentialRow).get(potentialCol);
        ActionResult action = objectAtPosition.sufferMovement();
        if (action.isMovementSuccessful()) {
            try {
                player.addItemToInventory((Item) objectAtPosition);
            } catch (Exception e) {
            } // ако не е било предмет не прави нищо
            map.get(potentialRow).set(potentialCol, new Blank());
            player.move(potentialRow, potentialCol);
        }
        notifier.notify(action);
    }

    // функциите move и attack са разделени!
    public synchronized void attackAt(int rowAddition, int colAddition) {
        int potentialRow = player.getRow() + rowAddition;
        int potentialCol = player.getColumn() + colAddition;
        ActionResult action = player.attack(map.get(potentialRow).get(potentialCol));
        if (action.getStatus().equals(Status.KILLED_ENEMY)) {
            map.get(potentialRow).set(potentialCol, new Blank());
        }
        notifier.notify(action);
    }

    public void useItemAt(int index) {
        try {
            Item usedItem = player.useItem(index);
            notifier.notify(new ActionResult(Status.ITEM_USED, usedItem));
        } catch (IllegalStateException illegalExc) {
            notifier.notify(new ActionResult(Status.ITEM_NOT_OWNED, null));
        } catch (ArrayIndexOutOfBoundsException indexOutExc) {
            notifier.notify(new ActionResult(Status.ITEM_INDEX_OUT_OF_RANGE, null));
        }
    }

    public String getCompletedQuestsInfo() {
        StringBuilder sb = new StringBuilder();
        Set<String> completedQuests = player.getCompletedQuests();
        for (String questName : completedQuests) {
            sb.append(questName + "\n");
        }
        return sb.toString();
    }

    public void setEventNotifier(Notifier notifier){
        this.notifier = notifier;
        player.setNotifier(notifier);
    }

    public void startQuest(Quest quest) {
        notifier.notify(player.startQuest(quest));
    }

    public void showPlayerStats(){
        player.showStats();
    }

    public void increasePlayerStats(int modifier){
        player.increaseStats(modifier);
    }

    public void decreasePlayerStats(int modifier){
        player.decreaseStats(modifier);
    }

    public void addPlayerHealth(int amount){
        player.addHealth(amount);
    }

    public void addPlayerMana(int amount){
        player.addMana(amount);
    }

    public int getPlayerRow(){
        return player.getRow();
    }

    public int getPlayerCol(){
        return player.getColumn();
    }

    public synchronized void generatePlayer(PlayerClass playerClass){
        this.player = new Player("Player", playerClass);
    }
}
