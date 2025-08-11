package view;

import backgroundActions.quests.QuestPool;
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

    public GameView(BaseVisualisator visualisator){
        this.visualisator = visualisator;
    }

    public Command getCommand(){
        return visualisator.getCommand();
    }

    public void showMessage(String message){
        visualisator.showMessage(message);
    }

    public void showMap(List<List<GameObject>> map, Player player){
        visualisator.showMap(map, player);
    }

    public void notify(ActionResult result){
        visualisator.notify(result);
    }

    public String requireUserInput(){
        return visualisator.requireUserInput();
    }

}
