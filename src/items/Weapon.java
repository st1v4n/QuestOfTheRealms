package items;

import model.Coordinates;
import model.playerClasses.Player;


public class Weapon extends Item{

    private final int attackBoost;
    private static final char mapVisualisation = 'w';

    public Weapon(int _attackBoost, Coordinates position){
        super(position, mapVisualisation);
        attackBoost = _attackBoost;
    }

    public int getAttackBoost(){
        return attackBoost;
    }

    @Override
    public void affect(Player player){
        player.addAttack(attackBoost);
    }

    @Override
    public String toString(){
        return "Weapon with " + attackBoost + " attack boost";
    }

}
