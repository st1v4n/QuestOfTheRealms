package commands;

public enum CommandNames {
    DOWN("down"),
    UP("up"),
    LEFT("left"),
    RIGHT("right"),
    PRINT_MAP("print-map"),
    STATS("stats"),
    ATTACK("attack"),
    USE("use"),
    VIEW_QUESTS("view-quests"),
    START_QUEST("start-quest"),
    COMPLETED_QUESTS("completed-quests"),
    HELP("help");

    public final String value;
    private CommandNames(String _value){
        value = _value;
    }

}
