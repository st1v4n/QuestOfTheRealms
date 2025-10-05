package commands;

import model.GameModel;
import model.actionResults.ActionResult;
import storages.FileStorage;
import view.GameView;

public class SaveCommand implements Command{

    @Override
    public ActionResult execute(GameModel model, GameView view){
        return FileStorage.save(model, "save.json");
    }

}
