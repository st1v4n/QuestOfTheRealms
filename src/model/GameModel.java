package model;

import backgroundActions.quests.Quest;
import backgroundActions.quests.QuestPool;
import model.enemy.Enemy;
import model.gameObjects.GameObject;
import model.items.Item;
import model.notifiers.Notifier;
import model.playerClasses.PlayerClass;
import view.GameView;
import java.util.List;

public class GameModel {

    /*
    Приемете, че все едно този клас го няма ... (малко е безсмислен)
     */

    private GameMap map;
    private transient Notifier notifier;

    public void init(GameView view){
        notifier = new Notifier();
        notifier.addObserver(view);
        map.setEventNotifier(notifier);
    }

    public GameModel(GameView view){
        init(view);
    }
    public void movePlayer(int rowAddition, int columnAddition){
            map.movePlayer(rowAddition, columnAddition);
    }

    public void attackAt(int rowAddition, int colAddition){
            map.attackAt(rowAddition, colAddition);
    }

    public void useItemAt(int index){
        map.useItemAt(index);
    }

    public void startQuest(String questName){
        Quest q = QuestPool.getQuestByName(questName);
        if(q == null)return;
        map.startQuest(q);
    }

    public String getCompletedQuestsInfo(){
        return map.getCompletedQuestsInfo();
    }

    public void showPlayerStats(){
        map.showPlayerStats();
    }

    public void increasePlayerStats(int modifier){
        map.increasePlayerStats(modifier);
    }

    public void decreasePlayerStats(int modifier){
        map.decreasePlayerStats(modifier);
    }

    public int getMapRows(){
        return map.getYBorder();
    }

    public int getMapCols(){
        return map.getXBorder();
    }

    public boolean isPositionEmpty(int row, int col){
        return map.isBlankPlace(row, col);
    }

    public void spawnEnemy(Enemy enemy, int row, int col){
        map.addEnemyAt(enemy, row, col);
    }

    public void spawnItem(Item item, int row, int col){
        map.addItemAt(item, row, col);
    }

    public void addPlayerHealth(int amount){
        map.addPlayerHealth(amount);
    }

    public void addPlayerMana(int amount){
        map.addPlayerMana(amount);
    }

    public List<List<GameObject>> getMapCopy(){
        return map.asList();
    }

    public int getPlayerRow(){
        return map.getPlayerRow();
    }

    public int getPlayerCol(){
        return map.getPlayerCol();
    }

    public void generatePlayer(PlayerClass playerClass){
        map.generatePlayer(playerClass);
    }

}
