package backgroundActions.statsIncreasers;

import model.playerClasses.Player;
import view.GameView;

public abstract class StatIncreaser extends Thread{

    protected final int amount;
    protected final GameView view;
    protected final Player player;

    public StatIncreaser(int amount, Player player, GameView view){
        this.amount = amount;
        this.player = player;
        this.view = view;
    }

    public abstract void increase();

}
