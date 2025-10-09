package backgroundActions.autoSavers;

import model.GameModel;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import storages.FileStorage;
import view.GameView;

public class JsonAutoSaver extends BaseAutoSaver {

    public static final String savingFileName = "src/save.json";

    public JsonAutoSaver(GameView view, GameModel model){
        super(view, model, savingFileName, new FileStorage());
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(AUTO_SAVE_INTERVAL);
            } catch (InterruptedException e){
                view.showMessage("Auto saving stopped working!");
            }
            // ключалката (за синхронизация) си е в самия save метод, затова тук няма синхронизация
            ActionResult result = storage.save(model, savingFileName);
            if(result.getStatus().equals(Status.GAME_SAVED)){
                view.showMessage(Status.GAME_SAVED.value);
            }
        }
    }
}
