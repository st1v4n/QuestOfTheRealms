package model.actionResults;

public class ActionResult {
    private final Status status;
    private final String description;

    public ActionResult(Status status, String description){
        this.status = status;
        this.description = description;
    }

    public Status getStatus(){
        return status;
    }

    public String getDescription(){
        return description;
    }

    public boolean didFail(){
        return status.equals(Status.ERROR);
    }

    public boolean didSucceed(){
        return status.equals(Status.SUCCESS);
    }

    public boolean didSucceedButNoUpdate(){
        return status.equals(Status.SUCCESS_BUT_NO_UPDATE);
    }
}
