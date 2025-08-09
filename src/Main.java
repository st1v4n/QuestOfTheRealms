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
            try{
                Player p = new Player("ivan", PlayerClass.MAGE);
                Enemy e = new Enemy(EnemyClass.BANDIT, new Coordinates(10,11));
                List<Enemy> allEnemies= new ArrayList<>();
                allEnemies.add(e);
                GameModel gm = new GameModel(p, allEnemies, mapFileName);
                GameView.visualiseMap(gm.getMap());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

}