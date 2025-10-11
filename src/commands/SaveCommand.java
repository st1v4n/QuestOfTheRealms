package commands;

import backgroundActions.autoSavers.JsonAutoSaver;
import model.GameModel;
import storages.FileStorage;
import view.GameView;

public class SaveCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.update(new FileStorage().save(model, JsonAutoSaver.savingFileName));
    }
}
