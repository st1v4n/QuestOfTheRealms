package locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomLocks {
    // прочетете коментара в EnemySpawner класа
    public static final ReentrantReadWriteLock modificationLock;
    static{
        modificationLock = new ReentrantReadWriteLock();
    }
}
