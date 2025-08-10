package model.enemy;

public enum EnemyClass {
    MONSTER("Monster"),
    BANDIT("Bandit"),
    BOSS("Boss");

    public final String value;

    private EnemyClass(String _value){
        value = _value;
    }
}
