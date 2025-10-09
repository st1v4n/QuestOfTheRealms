package commands;

import model.GameModel;
import view.GameView;

public class HelpCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for(CommandNames comName : CommandNames.values()){
            sb.append(comName.value + "\n");
        }
        view.showMessage(sb.toString());
    }
}
