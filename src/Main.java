import backgroundActions.autoSavers.JsonAutoSaver;
import controller.GameController;
import model.GameModel;
import model.playerClasses.PlayerClass;
import storages.FileStorage;
import view.GameView;
import view.visualisators.ConsoleVisualisator;

import java.io.FileNotFoundException;


public class Main {

    public static final String newMapFileName = "src/new.json";

    private static int getValueInInterval(int intervalStart, int intervalEnd, GameView gameView){
        String input = "";
        int selection = 0;
        do {
            input = gameView.requireUserInput();
            try {
                selection = Integer.parseInt(input);
            } catch (NumberFormatException e){
                gameView.showMessage("That is not even a number... (Please, enter a number)");
                selection = Integer.MIN_VALUE;
            }
        } while(selection < intervalStart || selection > intervalEnd);
        return selection;
    }

    private static PlayerClass getPlayerClass(GameView gameView){
        gameView.showMessage("Please, Enter your class:");
        for(int i=0;i<PlayerClass.values().length;++i){
            gameView.showMessage(i + ": " + PlayerClass.values()[i]);
        }
        int selection = getValueInInterval(0, PlayerClass.values().length - 1, gameView);
        return PlayerClass.values()[selection];
    }

    private static GameModel generateGame(GameView gameView){
        gameView.showMessage("Game Started Successfully! Please, choose how you would want to play:");
        gameView.showMessage("1. New game");
        gameView.showMessage("2. Existing game");
        int selection = getValueInInterval(1 , 2, gameView);
        GameModel model;
        switch (selection){
            case 1:
                try {
                    model = (new FileStorage()).load(gameView, newMapFileName);
                    model.generatePlayer(getPlayerClass(gameView));
                    model.init(gameView);
                    return model;
                } catch(FileNotFoundException e){
                    gameView.showMessage(e.getMessage());
                    System.exit(-1);
                }
            case 2:
                try {
                    model = (new FileStorage()).load(gameView, JsonAutoSaver.savingFileName);
                    model.init(gameView);
                    return model;
                } catch (FileNotFoundException e){
                    gameView.showMessage(e.getMessage());
                    System.exit(-1);
                }
        }
        return null;
    }

    public static void main(String[] args) {
        GameView gameView = new GameView(new ConsoleVisualisator());
        GameModel gameModel = generateGame(gameView);
        if(gameModel == null){
            gameView.showMessage("Something unexpected happened, can't play right now!");
            System.exit(0);
        }
        gameView.showMessage("Welcome! Type help to see available commands!");
        GameController gameController = new GameController(gameModel, gameView);
        while (true) {
            try {
                gameController.generateCommand();
            }
            catch(Exception exc){
                System.out.println(exc.getMessage());
            }
        }
    }
}
