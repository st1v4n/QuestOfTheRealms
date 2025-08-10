package commands;

import java.util.*;

public class CommandFactory {

    private final Map<String, Command> commands;

    public CommandFactory(){
        commands = new HashMap<>();
        commands.put(CommandNames.DOWN.value, new DownCommand());
        commands.put(CommandNames.UP.value, new UpCommand());
        commands.put(CommandNames.LEFT.value, new LeftCommand());
        commands.put(CommandNames.RIGHT.value, new RightCommand());
        commands.put(CommandNames.PRINT_MAP.value, new PrintMapCommand());
        commands.put(CommandNames.HELP.value, new HelpCommand());
        commands.put(CommandNames.STATS.value, new StatsCommand());
    }

    public Command create(String commandName){
        return commands.get(commandName);
    }
}
