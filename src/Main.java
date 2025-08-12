import controller.GameController;
import model.enemy.Boss;
import model.GameModel;
import model.enemy.Enemy;
import view.GameView;
import view.visualisators.ConsoleVisualisator;


public class Main {

    private static final String mapFileName = "src/view/mainMap.txt";

    public static void main(String[] args) {
        Enemy enemy = new Boss();
        GameView gameView = new GameView(new ConsoleVisualisator());
        GameModel gameModel = new GameModel(gameView, mapFileName);
        gameModel.addEnemyAt(enemy, 11,11);
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
