package backgroundActions.statsIncreasers;

import model.GameMap;
import model.notifiers.Notifier;
import model.playerClasses.Player;

public abstract class StatIncreaser extends Thread{

    protected final int amount;
    protected final Notifier notifier;
    protected final Player player;

    public StatIncreaser(int amount, Player player, Notifier notifier){
        this.amount = amount;
        this.player = player;
        this.notifier = notifier;
    }

    public abstract void increase();

}
