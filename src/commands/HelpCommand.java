package commands;

import model.GameModel;
import view.GameView;

import java.util.Set;

public class HelpCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        for(CommandNames commandName : CommandNames.values()){
            view.promptMessage("Available Command:");
            view.promptMessage(commandName.value);
        }
    }
}
