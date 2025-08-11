package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import view.GameView;

public class HelpCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for(CommandNames comName : CommandNames.values()){
            sb.append(comName.value + "\n");
        }
        return new ActionResult(Status.SUCCESS, sb.toString());
    }
}
