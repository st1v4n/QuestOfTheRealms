package backgroundActions.spawners;

import locks.CustomLocks;
import model.GameMap;
import model.items.Armor;
import model.items.Item;
import model.items.Potion;
import model.items.Weapon;
import view.GameView;

import java.util.Random;

public class ItemSpawner extends Thread{

    private final GameMap map;
    private final GameView view;
    private final Random randomGenerator;
    private static final int ITEM_SPAWN_INTERVAL = 25000;
    private static final int HEALTH_BOOST_CAP = 100;
    private static final int MANA_BOOST_CAP = 40;
    private static final int ATTACK_BOOST_CAP = 30;
    private static final int DEFENSE_BOOST_CAP = 25;

    public ItemSpawner(GameMap map, GameView view){
        this.map = map;
        this.view = view;
        this.randomGenerator = new Random();
    }

    private Item createItem(){
        int itemIndex = randomGenerator.nextInt(3);
        Item item = null;
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
    public void run(){
        while(true){
            try{
                Thread.sleep(ITEM_SPAWN_INTERVAL);
            }
            catch(InterruptedException e){
                view.showMessage("It seems like items dont want to spawn anymore...");
                return;
            }
            CustomLocks.modificationLock.readLock().lock();
            try {
                int row = 0;
                int col = 0;
                synchronized (map) {
                    do {
                        row = randomGenerator.nextInt(map.getYBorder());
                        col = randomGenerator.nextInt(map.getXBorder());
                    } while (!map.isBlankPlace(row, col));
                    map.addItemAt(createItem(), row, col);
                }
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }

}
