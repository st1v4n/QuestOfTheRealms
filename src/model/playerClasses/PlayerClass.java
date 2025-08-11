package model.playerClasses;

public enum PlayerClass {
    MAGE("Mage"),
    ROGUE("Rogue"),
    WARRIOR("Warrior");

    public final String value;

    private PlayerClass(String value){
        this.value = value;
    }
}
