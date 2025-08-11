import controller.GameController;
import model.items.Item;
import model.items.Potion;
import model.GameModel;
import model.enemy.Bandit;
import model.enemy.Enemy;
import model.playerClasses.Player;
import model.playerClasses.PlayerClass;
import view.GameView;

import java.util.Scanner;


public class Main {

    private static final String mapFileName = "src/view/mainMap.txt";

    public static void main(String[] args) {
        Player p = new Player("Ivan", PlayerClass.MAGE);
        Enemy enemy = new Bandit();
        Item item = new Potion(50, 20);
        GameView gameView = new GameView(new Scanner(System.in));
        GameModel gameModel = new GameModel(mapFileName, p);
        gameModel.addEnemyAt(enemy, 2,1);
        gameModel.addItemAt(item,12,1);
        GameController gameController = new GameController(gameModel, gameView);
        while (true) {
            try {
                gameController.generateCommand();
            }
            catch(Exception exc){
                System.out.println(exc.getMessage());
            }
        }
    }
}
