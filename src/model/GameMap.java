package model;

import backgroundActions.quests.Quest;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.enemy.Enemy;
import model.items.Item;
import model.playerClasses.Player;
import model.terrain.Blank;

import java.util.*;

public class GameMap {

    /*
    Картата е само карта и нейната отговорност е да знае къде какво има!
    (Погледнете романа, който написах като коментар в модела)
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

    public synchronized List<List<GameObject>> getMap(){
        return Collections.unmodifiableList(map);
    }

    public synchronized boolean isBlankPlace(int row, int col){
        return map.get(row).get(col).getClass().equals(Blank.class);
    }

    public synchronized ActionResult addEnemyAt(Enemy enemy, int row, int col){
        if(!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())){
            return new ActionResult(Status.ERROR, "Tried to spawn enemy on top of a game object...(mission failed successfully)");
        }
        map.get(row).set(col, enemy);
        return new ActionResult(Status.SUCCESS, "Successfully added enemy!");
    }

    public synchronized ActionResult addItemAt(Item item, int row, int col){
        if(!isBlankPlace(row, col) || (row == player.getRow() && col == player.getColumn())){
            return new ActionResult(Status.ERROR, "Tried to spawn item on top of a game object...(mission failed successfully)");
        }
        map.get(row).set(col, item);
        return new ActionResult(Status.SUCCESS, "Successfully added item!");
    }

    // функциите move и attack са разделени!
    public synchronized ActionResult movePlayer(int rowAddition, int colAddition){
        int potentialRow = player.getRow() + rowAddition;
        int potentialCol = player.getColumn() + colAddition;
        ActionResult action = map.get(potentialRow).get(potentialCol).collideOnMovement(player);
        if(action.didSucceed()){
            map.get(potentialRow).set(potentialCol, new Blank());
            player.move(potentialRow, potentialCol);
        }
        return action;
    }

    // функциите move и attack са разделени!
    public synchronized ActionResult attackAt(int rowAddition, int colAddition){
        int potentialRow = player.getRow() + rowAddition;
        int potentialCol = player.getColumn() + colAddition;
        ActionResult action = map.get(potentialRow).get(potentialCol).collideOnAttack(player);
        if(action.didSucceed()){
            map.get(potentialRow).set(potentialCol, new Blank());
        }
        return action;
    }

    public synchronized ActionResult useItemAt(int index){
        try{
            player.useItem(index);
        }
        catch(IllegalStateException illegalExc){
            return new ActionResult(Status.ERROR, "You do not have this item!");
        }
        catch(ArrayIndexOutOfBoundsException indexOutExc){
            return new ActionResult(Status.ERROR, "Invalid item index!");
        }
        return new ActionResult(Status.SUCCESS, "Successfully used the item!");
    }

    public ActionResult getCompletedQuests(){
        StringBuilder sb = new StringBuilder();
        Set<Quest> completedQuests = player.getCompletedQuests();
        for(Quest q : completedQuests){
            sb.append(q.getDescription() + "\n");
        }
        return new ActionResult(Status.SUCCESS, sb.toString());
    }

    public Player getPlayer(){
        return player;
    }

    public int getPlayerHealth(){
        return player.getHealth();
    }

    public int getPlayerMana(){
        return player.getMana();
    }

    public int getPlayerAttack(){
        return player.getAttack();
    }

    public int getPlayerDefense(){
        return player.getDefense();
    }

    public String getPlayerInventoryContent(){
        return player.getInventoryContent();
    }

}
