package backgroundActions.spawners;

import model.GameModel;
import model.enemy.Bandit;
import model.enemy.Enemy;
import model.enemy.Monster;
import view.GameView;


public class EnemySpawner extends Spawner{

    private static final int ENEMY_SPAWN_INTERVAL = 30000;

    public EnemySpawner(GameModel model, GameView view){
        super(ENEMY_SPAWN_INTERVAL, view, model);
    }

    private Enemy createEnemy(){
        int enemyIndex = randomGenerator.nextInt(2);
        switch (enemyIndex){
            case 0:
                return new Bandit();
            case 1:
                return new Monster();
            default:
                return null;
        }
    }

    @Override
    protected void spawn(int row, int col){
        model.spawnEnemy(createEnemy(), row, col);
    }

}
