package model.notifiers;

import model.actionResults.ActionResult;
import view.Observable;

import java.util.ArrayList;
import java.util.List;

public class Notifier {

    private final List<Observable> observables;

    public Notifier(){
        this.observables = new ArrayList<>();
    }

    public void addObservable(Observable object){
        observables.add(object);
    }

    public void removeObservable(Observable object){
        observables.remove(object);
    }
    public void notify(ActionResult result){
        for(Observable object : observables){
            object.update(result);
        }
    }
}
