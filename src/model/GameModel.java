package model;

import model.enemy.Enemy;
import model.playerClasses.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel {
    private final Player player;
    private Map<Coordinates, Enemy> enemies;
    public GameModel(Player _player, List<Enemy> _enemies){
        player = _player;
        enemies = new HashMap<>();
        for(int i = 0;i<_enemies.size();++i){
            enemies.put(_enemies.get(i).getPosition(), _enemies.get(i));
        }
    }
    public Enemy attackEnemy(Coordinates pos){
        Enemy enemyToAttack = enemies.get(pos);
        if(enemyToAttack == null){
            throw new IllegalArgumentException("No enemy on this position!");
        }
        player.attack(enemyToAttack);
        return enemyToAttack;
    }
}
