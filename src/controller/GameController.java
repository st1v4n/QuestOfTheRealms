package controller;

import backgroundActions.Day;
import backgroundActions.DayUpdater;
import commands.Command;
import model.GameModel;
import view.GameView;


public class GameController {

    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view){
        this.model = model;
        this.view = view;
    }

    public void generateCommand(){
        Command command = view.getCommand();
        if(command == null){
            view.showMessage("Invalid command! Use the help command to see all available commands!");
        }
        else{
            view.notify(command.execute(model, view));
        }
    }

}
