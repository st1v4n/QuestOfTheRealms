package view;

import commands.Command;
import commands.CommandFactory;
import model.actionResults.ActionResult;
import model.gameObjects.GameObject;
import model.playerClasses.Player;
import view.visualisators.BaseVisualisator;
import view.visualisators.ConsoleVisualisator;

import java.util.List;
import java.util.Scanner;

public class GameView {

    private final BaseVisualisator visualisator;
    private final Scanner scan;
    private final CommandFactory factory;

    public GameView(Scanner scan){
        visualisator = new ConsoleVisualisator();
        this.scan = scan;
        this.factory = new CommandFactory();
    }

    public Command getCommand(){
        System.out.print("Enter a command: ");
        String commandName;
        commandName = scan.next();
        return factory.create(commandName);
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void printMap(List<List<GameObject>> map, Player player){
        int rows = map.size();
        int columns = map.getFirst().size();
        for(int i = 0;i<rows;++i){
            for(int j = 0;j<columns;++j){
                if(i == player.getRow() && j == player.getColumn()){
                    System.out.print(visualisator.getVisualisation(player.toString()));
                    continue;
                }
                System.out.print(visualisator.getVisualisation(map.get(i).get(j).toString()));
            }
            System.out.println();
        }
    }

    public void notify(ActionResult result){
        System.out.println(result.getDescription());
    }

}
