package commands;

public enum CommandNames {
    DOWN("down"),
    UP("up"),
    LEFT("left"),
    RIGHT("right"),
    PRINT_MAP("print-map"),
    STATS("stats"),
    ATTACK_UP("attack-up"),
    ATTACK_DOWN("attack-down"),
    ATTACK_LEFT("attack-left"),
    ATTACK_RIGHT("attack-right"),
    HELP("help");

    public final String value;
    private CommandNames(String _value){
        value = _value;
    }

}
