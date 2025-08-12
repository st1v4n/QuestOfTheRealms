package model.enemy;

public class Boss extends Enemy{

    public static final int BOSS_HEALTH = 3600;
    public static final int BOSS_ATTACK = 300;

    public Boss(){
        super(BOSS_HEALTH, BOSS_ATTACK);
    }

    @Override
    public String toString(){
        return "Boss";
    }

    public String getInfo(){
        return "Boss" + super.getInfo();
    }
}
