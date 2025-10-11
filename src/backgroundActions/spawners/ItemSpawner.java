package backgroundActions.spawners;

import model.GameModel;
import model.items.Armor;
import model.items.Item;
import model.items.Potion;
import model.items.Weapon;
import view.GameView;

public class ItemSpawner extends Spawner{

    private static final int ITEM_SPAWN_INTERVAL = 25000;
    private static final int HEALTH_BOOST_CAP = 100;
    private static final int MANA_BOOST_CAP = 40;
    private static final int ATTACK_BOOST_CAP = 30;
    private static final int DEFENSE_BOOST_CAP = 25;

    public ItemSpawner(GameModel model, GameView view){
        super(ITEM_SPAWN_INTERVAL, view, model);
    }

    private Item createItem(){
        int itemIndex = randomGenerator.nextInt(3);
        switch (itemIndex){
            case 0:
                int healthBoost = randomGenerator.nextInt(HEALTH_BOOST_CAP) + 1;
                int manaBoost = randomGenerator.nextInt(MANA_BOOST_CAP) + 1;
                return new Potion(healthBoost, manaBoost);
            case 1:
                int attackBoost = randomGenerator.nextInt(ATTACK_BOOST_CAP) + 1;
                return new Weapon(attackBoost);
            case 2:
                int defenseBoost = randomGenerator.nextInt(DEFENSE_BOOST_CAP) + 1;
                return new Armor(defenseBoost);
            default:
                return null;
        }
    }

    @Override
    protected void spawn(int row, int col){
        model.spawnItem(createItem(), row, col);
    }

}
