package backgroundActions.statsIncreasers;

import model.GameModel;
import view.GameView;

public class ManaStatIncreaser extends StatIncreaser{

    private static final int MANA_INCREASE_AMOUNT = 10;
    private static final int MANA_INCREASE_INTERVAL = 22000;

    public ManaStatIncreaser(GameModel model, GameView view){
        super(MANA_INCREASE_INTERVAL, model, view);
    }

    @Override
    protected void increase(){
        model.addPlayerMana(MANA_INCREASE_AMOUNT);
    }

}
