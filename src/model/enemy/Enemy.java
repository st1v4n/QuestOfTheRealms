package model.enemy;

import model.Coordinates;

public class Enemy {
    private char mapVisualisation;
    private int health;
    private int attack;
    private final EnemyClass enemyClass;
    private Coordinates position;
    private boolean isAlive;

    private void constructEnemy(int _health,  int _attack, char _mapVisualisation){
        mapVisualisation = _mapVisualisation;
        health = _health;
        attack = _attack;
    }

    public Enemy(EnemyClass _enemyClass, Coordinates _position){
        enemyClass = _enemyClass;
        position = _position;
        isAlive = true;
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
            isAlive = false;
        }
    }

    public int getAttack(){
        return attack;
    }

    public int getRow(){
        return position.getRow();
    }

    public int getColumn(){
        return position.getColumn();
    }

    public Character getMapVisualisation(){
        return mapVisualisation;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public int getHealth(){
        return health;
    }

    @Override
    public String toString(){
        return enemyClass.value + " with " + health + " health remaining";
    }

}
