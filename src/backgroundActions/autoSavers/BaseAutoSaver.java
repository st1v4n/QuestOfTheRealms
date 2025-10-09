package backgroundActions.autoSavers;

import model.GameModel;
import model.notifiers.Notifier;
import storages.BaseStorage;

public class BaseAutoSaver extends Thread{

    protected final Notifier notifier;
    protected static final int AUTO_SAVE_INTERVAL = 60000;
    protected final GameModel model;
    protected final String fileName;
    protected final BaseStorage storage;

    public BaseAutoSaver(Notifier notifier, GameModel model, String fileName, BaseStorage storage){
        this.notifier = notifier;
        this.model = model;
        this.fileName = fileName;
        this.storage = storage;
    }
}
