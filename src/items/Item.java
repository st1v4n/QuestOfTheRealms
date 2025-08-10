package items;

import model.Coordinates;
import model.playerClasses.Player;

public abstract class Item {

    protected final Coordinates position;
    protected final char mapVisualisation;

    public abstract void affect(Player player);

    public Item(Coordinates _position, char _mapVisualisation){
        mapVisualisation = _mapVisualisation;
        position = _position;
    }

    public int getRow(){
        return position.getRow();
    }

    public int getColumn(){
        return position.getColumn();
    }

    public Character getMapVisualisation(){
        return mapVisualisation;
    }
}
