package model.notifiers;

import model.actionResults.ActionResult;
import view.Observer;

import java.util.ArrayList;
import java.util.List;

public class Notifier {

    private final List<Observer> observers;

    public Notifier(){
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer object){
        observers.add(object);
    }

    public void removeObserver(Observer object){
        observers.remove(object);
    }
    public void notify(ActionResult result){
        for(Observer object : observers){
            object.update(result);
        }
    }
}
