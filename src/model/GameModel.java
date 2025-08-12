package model;

import backgroundActions.Day;
import backgroundActions.DayUpdater;
import backgroundActions.spawners.EnemySpawner;
import backgroundActions.spawners.ItemSpawner;
import backgroundActions.quests.QuestPool;
import backgroundActions.statsIncreasers.HealthStatIncreaser;
import backgroundActions.statsIncreasers.ManaStatIncreaser;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.gameObjects.GameObject;
import model.items.Item;
import model.enemy.Enemy;
import model.mapGenerators.BaseMapGenerator;
import model.mapGenerators.MapGenerator;
import model.notifiers.Notifier;
import model.playerClasses.Player;
import view.GameView;

import java.util.InputMismatchException;
import java.util.List;

public class GameModel {

    private GameMap map;
    private final BaseMapGenerator mapGenerator;
    private final QuestPool questPool;
    private final Notifier notifier;
    private final Day daytimer;
    private final ItemSpawner itemSpawner;
    private final EnemySpawner enemySpawner;
    private final HealthStatIncreaser healthStatIncreaser;
    private final ManaStatIncreaser manaStatIncreaser;

    public GameModel(GameView view, String mapFileName){
        mapGenerator = new MapGenerator();
        notifier = new Notifier(view);
        try {
            map = mapGenerator.generateMapFromFile(mapFileName);
        }
        catch(InputMismatchException inputMismatchException){
            notifier.notify(new ActionResult(Status.ERROR, inputMismatchException.getMessage()));
            System.exit(-1);
        }
        questPool = new QuestPool(getPlayer(), notifier);
        daytimer = new Day();
        DayUpdater dayUpdater = new DayUpdater(daytimer, notifier, map.getPlayer());
        dayUpdater.start();
        itemSpawner = new ItemSpawner(map, notifier);
        itemSpawner.start();
        enemySpawner = new EnemySpawner(map, notifier);
        enemySpawner.start();
        healthStatIncreaser = new HealthStatIncreaser(map.getPlayer(), notifier);
        healthStatIncreaser.start();
        manaStatIncreaser = new ManaStatIncreaser(map.getPlayer(), notifier);
        manaStatIncreaser.start();
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

    public ActionResult useItemAt(int index){
        return map.useItemAt(index);
    }

    public ActionResult startQuest(int index){
        if(map.getPlayer().hasCompletedQuest(questPool.getQuestAtIndex(index))){
            return new ActionResult(Status.ERROR, "Quest already completed!");
        }
        return questPool.startQuest(index);
    }

    public String getAvailableQuestsInfo(){
        return questPool.getAvailableQuestsInfo();
    }

    public ActionResult getCompletedQuests(){
        return map.getCompletedQuests();
    }

}
