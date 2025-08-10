package items;

import model.Coordinates;
import model.playerClasses.Player;

public class Potion extends Item{

    private final int healthBoost;
    private final int manaBoost;
    private static final char mapVisualisation = 'p';

    public Potion(int _healthBoost, int _manaBoost, Coordinates _position){
        super(_position, mapVisualisation);
        healthBoost = _healthBoost;
        manaBoost = _manaBoost;
    }

    public int getHealthBoost(){
        return healthBoost;
    }

    public int getManaBoost(){
        return manaBoost;
    }

    @Override
    public void affect(Player player){
        player.addHealth(healthBoost);
        player.addMana(manaBoost);
    }

    @Override
    public String toString(){
        return "Potion with " + healthBoost + " health boost and " + manaBoost + " mana boost";
    }

}
