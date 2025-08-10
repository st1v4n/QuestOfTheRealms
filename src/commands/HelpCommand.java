package commands;

import model.GameModel;
import view.GameView;

import java.util.Set;

public class HelpCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        view.promptMessage("Available Commands:");
        for(CommandNames commandName : CommandNames.values()){
            view.promptMessage(commandName.value);
        }
    }
}
