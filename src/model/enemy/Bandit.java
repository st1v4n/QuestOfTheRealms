package model.enemy;

public class Bandit extends Enemy{

    public static final int BANDIT_HEALTH = 900;
    public static final int BANDIT_ATTACK = 240;

    public Bandit(){
        super(BANDIT_HEALTH, BANDIT_ATTACK);
    }

    @Override
    public String toString(){
        return "Bandit";
    }

    @Override
    public String getSpecificInformation(){
        return "Bandit" + super.getSpecificInformation();
    }
}
