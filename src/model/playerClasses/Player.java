package model.playerClasses;

import backgroundActions.quests.Quest;
import model.actionResults.ActionResult;
import model.items.Item;
import model.enemy.Enemy;
import model.inventory.Inventory;

import java.util.*;


public class Player {
    private final String name;
    private int health;
    private int mana;
    private int attack;
    private int defense;
    private final PlayerClass playerClass;
    private int row;
    private int column;
    private final Inventory inventory;
    private Enemy firstKilled = null;
    private final Set<Quest> completedQuests;

    private void constructPlayer(int health, int mana, int attack, int defense){
        this.health = health;
        this.mana = mana;
        this.attack = attack;
        this.defense = defense;
    }

    public Player(String name, PlayerClass playerClass){
        this.name = name;
        this.playerClass = playerClass;
        this.row = 1;
        this.column = 1;
        completedQuests = new HashSet<>();
        inventory = new Inventory();
        switch(playerClass){
            case MAGE: constructPlayer(ClassConstants.MAGE_HEALTH, ClassConstants.MAGE_MANA, ClassConstants.MAGE_ATTACK, ClassConstants.MAGE_DEFENSE);
                break;
            case WARRIOR: constructPlayer(ClassConstants.WARRIOR_HEALTH, ClassConstants.WARRIOR_MANA, ClassConstants.WARRIOR_ATTACK, ClassConstants.WARRIOR_DEFENSE);
                break;
            case ROGUE: constructPlayer(ClassConstants.ROGUE_HEALTH, ClassConstants.ROGUE_MANA, ClassConstants.ROGUE_ATTACK, ClassConstants.ROGUE_DEFENSE);
                break;
        }
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public synchronized void addHealth(int amount){
        health += amount;
    }

    public synchronized void addMana(int amount){
        mana += amount;
    }

    public void addAttack(int amount){
        attack += amount;
    }

    public void addDefense(int amount){
        defense += amount;
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

    public void addItemToInventory(Item item){
        inventory.addItem(item);
    }

    public void useItem(int index){
        Item item = inventory.getItemAt(index);
        if(item == null)throw new IllegalStateException("Invalid item!");
        item.affect(this);
        inventory.removeItem(item);
    }

    public void move(int row, int column){
        this.row = row;
        this.column = column;
    }

    public String getPlayerClass(){
        return playerClass.value;
    }

    public synchronized int getHealth(){
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public synchronized int getMana(){
        return mana;
    }

    public int getDefense(){
        return defense;
    }

    public String getInventoryContent(){
        return inventory.toString();
    }

    @Override
    public String toString(){
        return "Player";
    }

    public String getInfo(){
        return name + ": " + playerClass.value + " with " + health + " health remaining";
    }

    public void setFirstKilledEnemy(Enemy enemy){
        firstKilled = enemy;
    }

    public Enemy getFirstKilledEnemy(){
        return firstKilled;
    }

    public Set<Quest> getCompletedQuests(){
        return Collections.unmodifiableSet(completedQuests);
    }

    public void addCompletedQuest(Quest quest){
        completedQuests.add(quest);
    }

    public boolean hasCompletedQuest(Quest quest){
        return completedQuests.contains(quest);
    }

    public void increaseStats(int modifier){
        this.attack *= modifier;
        this.defense *= modifier;
    }

    public void decreaseStats(int modifier){
        this.attack /= modifier;
        this.defense /= modifier;
    }

}
