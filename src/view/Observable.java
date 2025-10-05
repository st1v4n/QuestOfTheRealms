package view;

import model.actionResults.ActionResult;

public interface Observable {
    public abstract void update(ActionResult result);
}
