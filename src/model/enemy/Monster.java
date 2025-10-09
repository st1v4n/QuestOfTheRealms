package model.enemy;

public class Monster extends Enemy{

    public static final int MONSTER_HEALTH = 1200;
    public static final int MONSTER_ATTACK = 220;

    public Monster(){
        super(MONSTER_HEALTH, MONSTER_ATTACK);
    }

    @Override
    public String toString(){
        return "Monster";
    }

    public String getSpecificInformation(){
        return "Monster" + super.getSpecificInformation();
    }
}
