package model.actionResults;

public enum Status {

    ITEM_PICKED("[-SYSTEM-] Item picked"),
    ITEM_ATTACKED("[-SYSTEM-] Tried to attack an item"),
    ITEM_NOT_OWNED("[-SYSTEM-] You don't own this item"),
    ITEM_INDEX_OUT_OF_RANGE("[-SYSTEM-] Invalid item index"),
    ITEM_USED("[-SYSTEM-] Used item"),
    GAME_SAVED("[-SYSTEM-] Game saved successfully..."),
    ERROR_WHILE_SAVING("[-SYSTEM-] Error while saving the game..."),
    SPAWNED_ENEMY("[-SYSTEM-] Spawned enemy..."),
    SPAWNED_ITEM("[-SYSTEM-] Spawned item..."),
    ATTACKED_ENEMY("[-SYSTEM-] Attacked enemy"),
    KILLED_ENEMY("[-SYSTEM-] Killed enemy"),
    MOVED("[-SYSTEM-] Player moved"),
    STARTED_QUEST("[-SYSTEM-] Started quest..."),
    STEPPED_ON_ENEMY("[-SYSTEM-] There is an enemy on the way"),
    QUEST_ALREADY_STARTED("[-SYSTEM-] Quest already started"),
    QUEST_ALREADY_COMPLETED("[-SYSTEM-] Quest already completed"),
    COMPLETED_QUEST("[-SYSTEM-] Completed quest..."),
    WRONG_SPAWN("[-SYSTEM-] Spawned a game object on an invalid place"),
    PLAYER_STATS_SHOWN("[-SYSTEM-] Showing player stats..."),
    WALL_ATTACKED("[-SYSTEM-] Attacked a wall"),
    HIT_A_WALL("[-SYSTEM-] A wall is blocking our way"),
    BLANK_ATTACKED("[-SYSTEM-] Attacked nothing"),
    HEALTH_INCREASED("[-SYSTEM-] Gained health..."),
    MANA_INCREASED("[-SYSTEM-] Gained mana..."),
    STATS_INCREASED("[-SYSTEM-] Gained some more attack and defense..."),
    STATS_DECREASED("[-SYSTEM-] Lost some attack and defense..."),
    GAME_OVER("[-SYSTEM-] You died...");

    public final String value;
    private Status(String value){
        this.value = value;
    }
}
