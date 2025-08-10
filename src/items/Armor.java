package items;

import model.Coordinates;
import model.playerClasses.Player;

public class Armor extends Item{

    private final int defenseBoost;
    private static final char mapVisualisation = 'a';

    public Armor(int _defenseBoost, Coordinates _position){
        super(_position, mapVisualisation);
        defenseBoost = _defenseBoost;
    }

    public int getDefenseBoost(){
        return defenseBoost;
    }

    @Override
    public void affect(Player player){
        player.addDefense(defenseBoost);
    }

    @Override
    public String toString(){
        return "Armor with " + defenseBoost + " defense boost";
    }

}
