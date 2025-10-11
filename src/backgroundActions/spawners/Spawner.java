package backgroundActions.spawners;

import locks.CustomLocks;
import model.GameModel;
import view.GameView;

import java.util.Random;

public abstract class Spawner extends Thread{

    private final int spawnInterval;
    protected final GameView view;
    protected final GameModel model;
    protected final Random randomGenerator;

    public Spawner(int spawnInterval, GameView view, GameModel model){
        this.spawnInterval = spawnInterval;
        this.view = view;
        this.model = model;
        randomGenerator = new Random();
    }

    protected abstract void spawn(int row, int col);

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(spawnInterval);
            }
            catch(InterruptedException e){
                view.showMessage("Spawning is gone...");
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
                synchronized (model) { // за да подсигурим, че ако минат едновременно spawner на чудовища и предмети и изберат едно и също място, няма да има проблеми
                    do {
                        row = randomGenerator.nextInt(model.getMapRows());
                        col = randomGenerator.nextInt(model.getMapCols());
                    } while (!model.isPositionEmpty(row, col));
                    spawn(row, col);
                }
            } finally {
                CustomLocks.modificationLock.readLock().unlock();
            }
        }
    }
}
