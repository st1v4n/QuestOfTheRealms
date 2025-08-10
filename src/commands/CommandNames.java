package commands;

public enum CommandNames {
    DOWN("down"),
    UP("up"),
    LEFT("left"),
    RIGHT("right"),
    PRINT_MAP("print-map"),
    STATS("stats"),
    HELP("help");

    public final String value;
    private CommandNames(String _value){
        value = _value;
    }

}
