package backgroundActions.spawners;

import model.GameMap;
import model.actionResults.ActionResult;
import model.actionResults.Status;
import model.enemy.Bandit;
import model.enemy.Enemy;
import model.enemy.Monster;
import model.notifiers.Notifier;

import java.util.Random;

public class EnemySpawner extends Thread{

    private final GameMap map;
    private final Notifier notifier;
    private final Random randomGenerator;
    private static final int ENEMY_SPAWN_INTERVAL = 30000;

    public EnemySpawner(GameMap map, Notifier notifier){
        this.map = map;
        this.notifier = notifier;
        this.randomGenerator = new Random();
    }

    private Enemy createEnemy(){
        int enemyIndex = randomGenerator.nextInt(2);
        Enemy item = null;
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
    public void run(){
        while(true){
            try{
                Thread.sleep(ENEMY_SPAWN_INTERVAL);
                int row = 0;
                int col = 0;
                do{
                    row = randomGenerator.nextInt(map.getYBorder());
                    col = randomGenerator.nextInt(map.getXBorder());
                }while(!map.isBlankPlace(row, col) || (row == map.getPlayer().getRow() && col == map.getPlayer().getColumn()));
                map.addEnemyAt(createEnemy(), row, col);
                notifier.notify(new ActionResult(Status.SUCCESS, "Spawned new Enemy on the map! Row: " + row + " ; Col: " + col));
            }
            catch(InterruptedException e){
                notifier.notify(new ActionResult(Status.ERROR, "Enemies are scared of you and dont want to spawn anymore :D"));
                return;
            }
        }
    }

}
