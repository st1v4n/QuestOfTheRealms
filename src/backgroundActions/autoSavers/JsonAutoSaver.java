package backgroundActions.autoSavers;

import locks.CustomLocks;
import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.notifiers.Notifier;
import storages.FileStorage;

public class JsonAutoSaver extends BaseAutoSaver {

    private static final String savingFileName = "src/save.json";

    public JsonAutoSaver(Notifier notifier, GameModel model){
        super(notifier, model, savingFileName);
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(AUTO_SAVE_INTERVAL);
            } catch (InterruptedException e){
                notifier.notify(new ActionResult(Status.ERROR, "Auto saving stopped working!"));
            }
            // ключалката (за синхронизация) си е в самия save метод, затова тук няма синхронизация
            notifier.notify(FileStorage.save(model, fileName));
        }
    }
}
