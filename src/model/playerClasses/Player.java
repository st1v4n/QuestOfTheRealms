package model.playerClasses;

import model.Coordinates;
import model.enemy.Enemy;

import java.util.Collection;

public class Player {
    public static final char mapVisualisation = 'X';
    private final String name;
    private int health;
    private int mana;
    private int attack;
    private int defense;
    private final PlayerClass playerClass;
    private Coordinates position = new Coordinates(1,1);

    private void constructPlayer(int _health, int _mana, int _attack, int _defense){
        health = _health;
        mana = _mana;
        attack = _attack;
        defense = _defense;
    }

    public Player(String _name, PlayerClass _playerClass){
        name = _name;
        playerClass = _playerClass;
        switch(playerClass){
            case MAGE: constructPlayer(ClassConstants.MAGE_HEALTH, ClassConstants.MAGE_MANA, ClassConstants.MAGE_ATTACK, ClassConstants.MAGE_DEFENSE);
                break;
            case WARRIOR: constructPlayer(ClassConstants.WARRIOR_HEALTH, ClassConstants.WARRIOR_MANA, ClassConstants.WARRIOR_ATTACK, ClassConstants.WARRIOR_DEFENSE);
                break;
            case ROGUE: constructPlayer(ClassConstants.ROGUE_HEALTH, ClassConstants.ROGUE_MANA, ClassConstants.ROGUE_ATTACK, ClassConstants.ROGUE_DEFENSE);
                break;
        }
    }

    public void attack(Enemy enemy){
        if(mana < ClassConstants.MANA_REQUIRED_FOR_ATTACK){
            throw new IllegalStateException("Not enough mana!");
        }
        mana -= ClassConstants.MANA_REQUIRED_FOR_ATTACK;
        int amount = this.attack;
        enemy.takeDamage(amount);
        defend(enemy.getAttack());
    }

    public void defend(int incomingDamage){
        int amount = incomingDamage - ClassConstants.DEFENSE_MULTIPLIER * defense;
        health -= amount;
        if(health <= 0){
            //...
        }
    }

    public void useItem(){

    }

    public void run(){

    }
}
