package model.enemy;

import model.Coordinates;
import model.playerClasses.PlayerClass;

public class Enemy {
    private char mapVisualisation;
    private int health;
    private int attack;
    private final EnemyClass enemyClass;
    private Coordinates position;

    private void constructEnemy(int _health,  int _attack, char _mapVisualisation){
        mapVisualisation = _mapVisualisation;
        health = _health;
        attack = _attack;
    }

    public Enemy(EnemyClass _enemyClass, Coordinates _position){
        enemyClass = _enemyClass;
        position = _position;
        switch(enemyClass){
            case MONSTER: constructEnemy(EnemyConstants.MONSTER_HEALTH, EnemyConstants.MONSTER_ATTACK, EnemyConstants.MONSTER_MAP_VISUALISATION);
                break;
            case BANDIT: constructEnemy(EnemyConstants.BANDIT_HEALTH, EnemyConstants.BANDIT_ATTACK, EnemyConstants.BANDIT_MAP_VISUALISATION);
                break;
            case BOSS: constructEnemy(EnemyConstants.BOSS_HEALTH, EnemyConstants.BOSS_ATTACK, EnemyConstants.BOSS_MAP_VISUALISATION);
                break;
        }
    }

    public void takeDamage(int amount){
        health -= amount;
        if(health <= 0){
            mapVisualisation = EnemyConstants.DEAD_ENEMY_VISUALISATION;
        }
    }

    public int getAttack(){
        return attack;
    }

    public Coordinates getPosition(){
        return position;
    }

    public Character getMapVisualisation(){
        return mapVisualisation;
    }

}
