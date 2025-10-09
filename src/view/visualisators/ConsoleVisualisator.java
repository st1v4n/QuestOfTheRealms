package view.visualisators;

import commands.Command;
import model.actionResults.ActionResult;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleVisualisator implements BaseVisualisator{

    private final Map<String, Character> visualisations;
    private final Scanner scan;

    public ConsoleVisualisator(){
        this.scan = new Scanner(System.in);
        visualisations = new HashMap<>();
        visualisations.put("Player", '@');
        visualisations.put("Bandit", 'B');
        visualisations.put("Monster", 'M');
        visualisations.put("Boss", 'S');
        visualisations.put("Potion", 'p');
        visualisations.put("Weapon", 'w');
        visualisations.put("Armor", 'a');
        visualisations.put("Wall", '*');
        visualisations.put("Blank", ' ');
    }

    private Object getVisualisation(String obj){
        return visualisations.get(obj);
    }

    @Override
    public Command getCommand(){
        String commandName;
        commandName = scan.next();
        return factory.create(commandName);
    }

    @Override
    public void showMessage(String message){
        System.out.println(message);
    }

    @Override
    public void showMap(List<List<GameObject>> map, Player player){
        int rows = map.size();
        int columns = map.getFirst().size();
        for(int i = 0;i<rows;++i){
            for(int j = 0;j<columns;++j){
                if(i == player.getRow() && j == player.getColumn()){
                    System.out.print(getVisualisation(player.toString()));
                    continue;
                }
                System.out.print(getVisualisation(map.get(i).get(j).toString()));
            }
            System.out.println();
        }
    }

    @Override
    public void notify(ActionResult result){
        System.out.println(result.getStatus().value);
        if(result.getEntity() != null){
            System.out.println(result.getEntity().getSpecificInformation());
        }
    }

    @Override
    public String requireUserInput(){
        return scan.next();
    }

}
