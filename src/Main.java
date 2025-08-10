import controller.GameController;
import items.Item;
import items.Weapon;
import model.Coordinates;
import model.GameModel;
import model.enemy.Enemy;
import model.enemy.EnemyClass;
import model.playerClasses.Player;
import model.playerClasses.PlayerClass;
import view.GameView;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String mapFileName = "src/view/mainMap.txt";

    public static void main(String[] args) {
        Player p = new Player("Ivan", PlayerClass.MAGE);
        Enemy e = new Enemy(EnemyClass.BANDIT, new Coordinates(2, 1));
        Item i = new Weapon(15, new Coordinates(12, 1));
        List<Enemy> allEnemies = new ArrayList<>();
        List<Item> allItems = new ArrayList<>();
        allEnemies.add(e);
        allItems.add(i);
        GameModel gm = new GameModel(p, allEnemies, allItems, mapFileName);
        GameView gw = new GameView();
        GameController gc = new GameController(gm, gw);
        while (true) {
            try {
                gc.generateCommand();
            }
            catch(Exception exc){
                System.out.println(exc.getMessage());
            }
        }
    }
}
