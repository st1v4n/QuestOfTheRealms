import controller.GameController;
import model.GameModel;
import view.GameView;
import view.visualisators.ConsoleVisualisator;


public class Main {

    private static final String mapFileName = "src/view/mainMap.txt";

    public static void main(String[] args) {
        GameView gameView = new GameView(new ConsoleVisualisator());
        GameModel gameModel = new GameModel(gameView, mapFileName);
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
