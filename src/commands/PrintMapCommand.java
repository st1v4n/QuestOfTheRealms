package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class PrintMapCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        synchronized (model.map()) {
            view.showMap(model.map().asList(), model.getPlayer());
            return new ActionResult(Status.SUCCESS, "Printed map");
        }
    }
}
