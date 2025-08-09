package model;

import model.enemy.Enemy;
import model.playerClasses.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel {

    private final GameMap map;

    public GameModel(Player player, List<Enemy> _enemies, String mapFileName){
        Map<Coordinates, Enemy> enemiesMap = new HashMap<>();
        for(int i = 0;i<_enemies.size();++i){
            enemiesMap.put(_enemies.get(i).getPosition(), _enemies.get(i));
        }
        map = new GameMap(mapFileName, player, enemiesMap);
    }

    public Enemy attackEnemy(Coordinates pos){
        Enemy enemyToAttack = map.getEnemies().get(pos);
        if(enemyToAttack == null){
            throw new IllegalArgumentException("No enemy on this position!");
        }
        map.getPlayer().attack(enemyToAttack);
        return enemyToAttack;
    }

    public GameMap getMap(){
        return map;
    }

}
