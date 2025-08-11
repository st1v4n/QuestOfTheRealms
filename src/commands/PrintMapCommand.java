package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class PrintMapCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        view.printMap(model.getMap(), model.getPlayer());
        return new ActionResult(Status.SUCCESS, "Printed map");
    }
}
