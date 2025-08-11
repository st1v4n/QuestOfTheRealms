package model;

import model.actionResults.ActionResult;
import model.gameObjects.GameObject;
import model.items.Item;
import model.enemy.Enemy;
import model.mapGenerators.MapGenerator;
import model.playerClasses.Player;
import java.util.List;

public class GameModel {

    private final GameMap map;

    public GameModel(String mapFileName, Player player){
        map = MapGenerator.generateNewMapFromFile(mapFileName, player);
    }

    public List<List<GameObject>> getMap(){
        return map.getMap();
    }

    public int getMapColumns(){
        return map.getXBorder();
    }

    public int getMapRows(){
        return map.getYBorder();
    }

    public ActionResult movePlayer(int rowAddition, int columnAddition){
        return map.movePlayer(rowAddition, columnAddition);
    }

    public int getPlayerHealth(){
        return map.getPlayerHealth();
    }

    public int getPlayerMana(){
        return map.getPlayerMana();
    }

    public int getPlayerAttack(){
        return map.getPlayerAttack();
    }

    public int getPlayerDefense(){
        return map.getPlayerDefense();
    }

    public String getPlayerInventoryContent(){
        return map.getPlayerInventoryContent();
    }

    public ActionResult addEnemyAt(Enemy enemy, int row, int col){
        return map.addEnemyAt(enemy, row, col);
    }

    public ActionResult addItemAt(Item item, int row, int col){
        return map.addItemAt(item, row, col);
    }

    public Player getPlayer(){
        return map.getPlayer();
    }

    public ActionResult attackAt(int rowAddition, int colAddition){
        return map.attackAt(rowAddition, colAddition);
    }

}
