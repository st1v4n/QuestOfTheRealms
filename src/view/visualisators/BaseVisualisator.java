package view.visualisators;

import commands.Command;
import commands.CommandFactory;
import model.actionResults.ActionResult;
import model.gameObjects.GameObject;
import model.playerClasses.Player;

import java.util.List;

public interface BaseVisualisator {

    public static final CommandFactory factory = new CommandFactory();
    public abstract Command getCommand();
    public abstract void showMessage(String message);
    public abstract void showMap(List<List<GameObject>> map, Player player);
    public abstract void notify(ActionResult result);
    public abstract String requireUserInput();

}
