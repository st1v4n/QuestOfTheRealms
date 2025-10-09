package storages;

import model.GameModel;
import model.actionResults.ActionResult;
import view.GameView;

import java.io.FileNotFoundException;

public interface BaseStorage {
    public abstract ActionResult save(GameModel model, String fileName);
    public abstract GameModel load(GameView view, String fileName) throws FileNotFoundException;
}
