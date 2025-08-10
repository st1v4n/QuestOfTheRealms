package controller;

import commands.Command;
import commands.CommandFactory;
import model.GameModel;
import view.GameView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameController {

    private final GameModel model;
    private final GameView view;
    private final Scanner scan;
    private final CommandFactory factory;

    public GameController(GameModel _model, GameView _view){
        model = _model;
        view = _view;
        scan = new Scanner(System.in);
        factory = new CommandFactory();
    }

    public void generateCommand(){
        view.promptMessage("Enter a command: ");
        String commandName;
        commandName = scan.next();
        Command command = factory.create(commandName);
        if(command == null){
            view.promptMessage("Invalid command! Use the help command to see all available commands!");
        }
        else{
            command.execute(model, view);
        }
    }

}
