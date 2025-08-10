package model.playerClasses;

import items.Item;
import model.Coordinates;
import model.enemy.Enemy;
import model.inventory.Inventory;

public class Player {
    private final char mapVisualisation = '@';
    private final String name;
    private int health;
    private int mana;
    private int attack;
    private int defense;
    private final PlayerClass playerClass;
    private Coordinates position = new Coordinates(1,1);
    private final Inventory inventory;

    private void constructPlayer(int _health, int _mana, int _attack, int _defense){
        health = _health;
        mana = _mana;
        attack = _attack;
        defense = _defense;
    }

    public Player(String _name, PlayerClass _playerClass){
        name = _name;
        playerClass = _playerClass;
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
        return position.getRow();
    }

    public int getColumn(){
        return position.getColumn();
    }

    public Character getMapVisualisation(){
        return mapVisualisation;
    }

    public void addHealth(int amount){
        health += amount;
    }

    public void addMana(int amount){
        mana += amount;
    }

    public void addAttack(int amount){
        attack += amount;
    }

    public void addDefense(int amount){
        defense += amount;
    }

    public String attack(Enemy enemy){
        if(mana < ClassConstants.MANA_REQUIRED_FOR_ATTACK){
            throw new IllegalStateException("Not enough mana!");
        }
        mana -= ClassConstants.MANA_REQUIRED_FOR_ATTACK;
        int amount = this.attack;
        enemy.takeDamage(amount);
        defend(enemy.getAttack());
        return this.toString() + " Attacked " + enemy.toString() + " and dealt " + amount + " damage!";
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
        if(item == null)throw new IllegalArgumentException("Invalid index!");
        item.affect(this);
        inventory.removeItem(item);
    }

    public void move(int row, int column){
        position.setRow(row);
        position.setColumn(column);
    }

    public String getPlayerClass(){
        return playerClass.value;
    }

    public int getHealth(){
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public int getMana(){
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
        return name + ": " + playerClass.value + " with " + health + " health remaining";
    }

}
