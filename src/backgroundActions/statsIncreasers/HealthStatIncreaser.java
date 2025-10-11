package backgroundActions.statsIncreasers;

import model.GameModel;
import view.GameView;

public class HealthStatIncreaser extends StatIncreaser{

    private static final int HEALTH_INCREASE_AMOUNT = 100;
    private static final int HEALTH_INCREASE_INTERVAL = 25000;

    public HealthStatIncreaser(GameModel model, GameView view){
        super(HEALTH_INCREASE_INTERVAL, model, view);
    }

    @Override
    protected void increase(){
        model.addPlayerHealth(HEALTH_INCREASE_AMOUNT);
    }
}
