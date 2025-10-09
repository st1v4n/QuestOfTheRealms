package backgroundActions.autoSavers;

import model.GameModel;
import storages.BaseStorage;
import view.GameView;

public class BaseAutoSaver extends Thread{

    protected final GameView view;
    protected static final int AUTO_SAVE_INTERVAL = 60000;
    protected final GameModel model;
    protected final String fileName;
    protected final BaseStorage storage;

    public BaseAutoSaver(GameView view, GameModel model, String fileName, BaseStorage storage){
        this.view = view;
        this.model = model;
        this.fileName = fileName;
        this.storage = storage;
    }
}
