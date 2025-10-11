package controller;

import backgroundActions.Day;
import backgroundActions.DayUpdater;
import backgroundActions.autoSavers.BaseAutoSaver;
import backgroundActions.autoSavers.JsonAutoSaver;
import backgroundActions.spawners.EnemySpawner;
import backgroundActions.spawners.ItemSpawner;
import backgroundActions.statsIncreasers.HealthStatIncreaser;
import backgroundActions.statsIncreasers.ManaStatIncreaser;
import commands.Command;
import locks.CustomLocks;
import model.GameModel;
import view.GameView;


public class GameController {

    private final GameModel model;
    private final GameView view;
    private final ItemSpawner itemSpawner;
    private final EnemySpawner enemySpawner;
    private final HealthStatIncreaser healthStatIncreaser;
    private final ManaStatIncreaser manaStatIncreaser;
    private final BaseAutoSaver autoSaver;
    private Day daytimer;

    public GameController(GameModel model, GameView view){
        this.model = model;
        this.view = view;
        daytimer = new Day();
        DayUpdater dayUpdater = new DayUpdater(daytimer, view, model);
        dayUpdater.start();
        itemSpawner = new ItemSpawner(model, view);
        itemSpawner.start();
        enemySpawner = new EnemySpawner(model, view);
        enemySpawner.start();
        healthStatIncreaser = new HealthStatIncreaser(model, view);
        healthStatIncreaser.start();
        manaStatIncreaser = new ManaStatIncreaser(model, view);
        manaStatIncreaser.start();
        autoSaver = new JsonAutoSaver(view, model);
        autoSaver.start();
    }

    public void generateCommand(){
        Command command = view.getCommand();
        if (command == null) {
            view.showMessage("Invalid command! Use the help command to see all available commands!");
        } else {
            command.execute(model, view);
        }
    }

}
