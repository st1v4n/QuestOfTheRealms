package commands;

import model.GameModel;
import view.GameView;

public class UseCommand implements Command{

    @Override
    public void execute(GameModel model, GameView view){
        String flag = view.requireUserInput();
        try{
            int index = Integer.parseInt(flag);
            model.useItemAt(index);
        }
        catch(Exception e){
            view.showMessage("You have entered invalid index");
        }
    }
}
