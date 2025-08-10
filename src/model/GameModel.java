package model;

import items.Item;
import model.enemy.Enemy;
import model.playerClasses.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel {

    private final GameMap map;

    public GameModel(Player player, List<Enemy> _enemies, List<Item> _items, String mapFileName){
        Map<Coordinates, Enemy> enemiesMap = new HashMap<>();
        for(int i = 0;i<_enemies.size();++i){
            Enemy current = _enemies.get(i);
            enemiesMap.put(new Coordinates(current.getRow(), current.getColumn()), current);
        }
        Map<Coordinates, Item> itemsMap = new HashMap<>();
        for(int i = 0;i<_items.size();++i){
            Item current = _items.get(i);
            itemsMap.put(new Coordinates(current.getRow(), current.getColumn()), current);
        }
        map = new GameMap(mapFileName, player, enemiesMap, itemsMap);
    }

    public Enemy attackEnemy(Coordinates pos){
        Enemy enemyToAttack = map.getEnemyAtPosition(pos);
        if(enemyToAttack == null){
            throw new IllegalArgumentException("No enemy on this position!");
        }
        map.performPlayerAttackOn(enemyToAttack);
        return enemyToAttack;
    }

    public List<List<Character>> getMap(){
        return map.getMap();
    }

    public int getMapColumns(){
        return map.getXBorder();
    }

    public int getMapRows(){
        return map.getYBorder();
    }

    public String movePlayer(int rowAddition, int columnAddition){
        return map.movePlayer(rowAddition, columnAddition);
    }

    public String playerInfo(){
        return map.getPlayerInfo();
    }

}
