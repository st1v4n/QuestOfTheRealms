package model.playerClasses;

import backgroundActions.autoSavers.JsonAutoSaver;
import backgroundActions.quests.Quest;
import backgroundActions.quests.QuestPool;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.Entity;
import model.gameObjects.GameObject;
import model.items.Item;
import model.enemy.Enemy;
import model.inventory.Inventory;
import model.notifiers.Notifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Player implements Entity {
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
    private Set<String> startedQuests;
    private Set<String> completedQuests;
    private transient Notifier notifier;

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
        startedQuests = new HashSet<>();
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

    public synchronized void addHealth(int amount) {
        health += amount;
        notifier.notify(new ActionResult(Status.HEALTH_INCREASED, null));
    }

    public synchronized void addMana(int amount) {
        mana += amount;
        notifier.notify(new ActionResult(Status.MANA_INCREASED, null));
        checkForCompletedQuests();
    }

    public synchronized void addAttack(int amount) {
        attack += amount;
    }

    public synchronized void addDefense(int amount) {
        defense += amount;
        checkForCompletedQuests();
    }

    public synchronized ActionResult attack(GameObject target) {
        if (mana < ClassConstants.MANA_REQUIRED_FOR_ATTACK) {
            throw new IllegalStateException("Not enough mana!");
        }
        int amount = this.attack;
        ActionResult resultFromAttacking = target.sufferAttack(amount);
        if (resultFromAttacking.isTargetEnemy()) { // ако реално сме ударили чудовище а не нещо друго
            defend(((Enemy)target).getAttack());
            mana -= ClassConstants.MANA_REQUIRED_FOR_ATTACK;
            checkForCompletedQuests();
            if (resultFromAttacking.getStatus().equals(Status.KILLED_ENEMY)) {
                if (firstKilled == null) {
                    firstKilled = (Enemy) target;
                    checkForCompletedQuests();
                }
            }
        }
        return resultFromAttacking;
    }

    private synchronized void defend(int incomingDamage) { // не трябва да е synchronised попринцип, защото се извиква само от attack, а тя си е synchronised, но все пак
        int amount = incomingDamage - ClassConstants.DEFENSE_MULTIPLIER * defense;
        health -= amount;
        if (health <= 0) {
            notifier.notify(new ActionResult(Status.GAME_OVER, null));
            try {
                Path saveFilePath = Path.of(JsonAutoSaver.savingFileName);
                Files.delete(saveFilePath);
            } catch (IOException e) {
                System.exit(-1);
            }
            System.exit(0);
        }
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public synchronized Item useItem(int index) {
        Item item = inventory.getItemAt(index);
        if (item == null) throw new IllegalStateException("Invalid item!");
        item.affect(this);
        inventory.removeItem(item);
        return item;
    }

    public synchronized void move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String getPlayerClass() {
        return playerClass.value;
    }

    public synchronized int getHealth() {
        return health;
    }

    public synchronized int getAttack() {
        return attack;
    }

    public synchronized int getMana() {
        return mana;
    }

    public synchronized int getDefense() {
        return defense;
    }

    public String getInventoryContent() {
        return inventory.toString();
    }

    @Override
    public String toString() {
        return "Player";
    }

    public Enemy getFirstKilledEnemy() {
        return firstKilled;
    }

    public synchronized Set<String> getCompletedQuests() {
        return new HashSet<>(completedQuests);
    }

    public synchronized ActionResult startQuest(Quest quest) {
        if (startedQuests.contains(quest.getName())) {
            return new ActionResult(Status.QUEST_ALREADY_STARTED, quest);
        }
        if (completedQuests.contains(quest.getName())) {
            return new ActionResult(Status.QUEST_ALREADY_COMPLETED, quest);
        }
        startedQuests.add(quest.getName());
        checkForCompletedQuests(); // ако е завършен преди да е добавен
        return new ActionResult(Status.STARTED_QUEST, quest);
    }

    private synchronized void checkForCompletedQuests() {
        List<String> currently_completed = new ArrayList<>();
        for (String questName : startedQuests) {
            if (QuestPool.getQuestByName(questName).isCompleted(this)) {
                currently_completed.add(questName);
                completedQuests.add(questName);
                notifier.notify(new ActionResult(Status.COMPLETED_QUEST, QuestPool.getQuestByName(questName)));
            }
        }
        for(String questName : currently_completed){
            startedQuests.remove(questName);
        }
    }

    public synchronized void increaseStats(int modifier){
        this.attack *= modifier;
        this.defense *= modifier;
        notifier.notify(new ActionResult(Status.STATS_INCREASED, null));
        checkForCompletedQuests();
    }

    public synchronized void decreaseStats(int modifier){
        this.attack /= modifier;
        this.defense /= modifier;
        notifier.notify(new ActionResult(Status.STATS_DECREASED, null));
        checkForCompletedQuests();
    }

    public void setNotifier(Notifier notifier){
        this.notifier = notifier;
    }

    @Override
    public synchronized String getSpecificInformation(){
        return "Health: " + health + "\n" +
                "Mana: " + mana + "\n" +
                "Attack: " + attack + "\n" +
                "Defense: " + defense + "\n" +
                "Inventory: " + getInventoryContent();
    }

    public void showStats(){
        notifier.notify(new ActionResult(Status.PLAYER_STATS_SHOWN, this));
    }
}
