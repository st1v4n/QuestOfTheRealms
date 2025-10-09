package backgroundActions.quests;
import model.gameObjects.Entity;
import model.playerClasses.Player;

import java.util.Objects;
import java.util.function.Function;

public class Quest implements Entity {


    private final String description;
    private final String name;
    private transient Function<Player, Boolean> func;

    public Quest(String name, String description, Function<Player, Boolean> func){
        this.name = name;
        this.description = description;
        this.func = func;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    public boolean isCompleted(Player player){
        return func.apply(player);
    }

    @Override
    public boolean equals(Object other){
        if(this == other)return true;
        if(other == null || this.getClass() != other.getClass())return false;
        Quest helper = (Quest)other;
        return helper.description.equals(this.description);
    }

    @Override
    public int hashCode(){
        return Objects.hash(description);
    }

    @Override
    public String getSpecificInformation(){
        return getDescription();
    }
}
