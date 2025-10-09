package model.actionResults;

import model.gameObjects.Entity;

public class ActionResult {
    private final Status status;
    private final Entity entity;

    public ActionResult(Status status, Entity entity){
        this.status = status;
        this.entity = entity;
    }

    public Status getStatus(){
        return status;
    }

    public Entity getEntity(){
        return entity;
    }

    public boolean isTargetEnemy(){
        return status.equals(Status.ATTACKED_ENEMY) || status.equals(Status.KILLED_ENEMY);
    }

    public boolean isMovementSuccessful(){
        return status.equals(Status.MOVED) || status.equals(Status.ITEM_PICKED);
    }

}
