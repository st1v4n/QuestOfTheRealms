package model.notifiers;

import model.actionResults.ActionResult;
import view.GameView;

public class Notifier {

    private final GameView view;

    public Notifier(GameView view){
        this.view = view;
    }

    public void notify(ActionResult result){
        view.notify(result);
    }
}
