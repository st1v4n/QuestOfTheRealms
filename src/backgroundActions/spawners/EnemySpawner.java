package backgroundActions.spawners;

import locks.CustomLocks;
import model.GameMap;
import model.enemy.Bandit;
import model.enemy.Enemy;
import model.enemy.Monster;
import view.GameView;

import java.util.Random;

public class EnemySpawner extends Thread{

    private final GameMap map;
    private final GameView view;
    private final Random randomGenerator;
    private static final int ENEMY_SPAWN_INTERVAL = 30000;

    public EnemySpawner(GameMap map, GameView view){
        this.map = map;
        this.view = view;
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
            }
            catch(InterruptedException e){
                view.showMessage("Enemies are scared of you and dont want to spawn anymore :D");
                return;
            }
            // понеже не искаме да можем да правим промени по картата/играча докато запазваме във файл
            // докато се играе нормално, си имаме и нормално конкурентно изпълнение на нишките
            // но в момента, в който искаме да запазим картата
            // искаме играта да ,,замръзне''
            CustomLocks.modificationLock.readLock().lock();
            try {
                int row = 0;
                int col = 0;
                synchronized (map) { // за да подсигурим, че ако минат едновременно spawner на чудовища и предмети и изберат едно и също място, няма да има проблеми
                    do {
                        row = randomGenerator.nextInt(map.getYBorder());
                        col = randomGenerator.nextInt(map.getXBorder());
                    } while (!map.isBlankPlace(row, col));
                    map.addEnemyAt(createEnemy(), row, col);
                }
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }

}
