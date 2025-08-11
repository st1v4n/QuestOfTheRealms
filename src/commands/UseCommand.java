package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class UseCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        String flag = view.requireUserInput();
        try{
            int index = Integer.parseInt(flag);
            return model.useItemAt(index);
        }
        catch(Exception e){
            return new ActionResult(Status.ERROR, "You've entered invalid flag!");
        }
    }
}
