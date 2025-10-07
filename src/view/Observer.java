package view;

import model.actionResults.ActionResult;

public interface Observer {
    public abstract void update(ActionResult result);
}
