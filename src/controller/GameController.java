package controller;

import commands.Command;
import locks.CustomLocks;
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
        else {
            CustomLocks.modificationLock.readLock().lock();
            try {
                view.update(command.execute(model, view));
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }

}
