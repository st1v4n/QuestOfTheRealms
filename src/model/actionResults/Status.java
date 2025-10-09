package model.actionResults;

public enum Status {

    ITEM_PICKED("Item picked"),
    ITEM_ATTACKED("Tried to attack an item"),
    ITEM_NOT_OWNED("You don't own this item"),
    ITEM_INDEX_OUT_OF_RANGE("Invalid item index"),
    ITEM_USED("Used item"),
    GAME_SAVED("Game saved successfully"),
    ERROR_WHILE_SAVING("Error while saving the game"),
    SPAWNED_ENEMY("Spawned enemy"),
    SPAWNED_ITEM("Spawned item"),
    ATTACKED_ENEMY("Attacked enemy"),
    KILLED_ENEMY("Killed enemy"),
    MOVED("Player moved"),
    STARTED_QUEST("Started quest"),
    STEPPED_ON_ENEMY("There is an enemy on the way"),
    QUEST_ALREADY_STARTED("Quest already started"),
    QUEST_ALREADY_COMPLETED("Quest already completed"),
    COMPLETED_QUEST("Completed quest"),
    WRONG_SPAWN("Spawned a game object on an invalid place"),
    PLAYER_STATS_SHOWN("Showing player stats"),
    WALL_ATTACKED("Attacked a wall"),
    HIT_A_WALL("A wall is blocking our way"),
    BLANK_ATTACKED("Attacked nothing");


    public final String value;
    private Status(String value){
        this.value = value;
    }
}
