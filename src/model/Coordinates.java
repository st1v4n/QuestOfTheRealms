package model;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates(int _x, int _y){
        x = _x;
        y = _y;
    }
    @Override
    public boolean equals(Object other){
        if(this==other)return true;
        if(other == null || this.getClass() != other.getClass())return false;
        Coordinates help = (Coordinates) other;
        return x == help.x && y == help.y;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int _x){
        x = _x;
    }
    public void setY(int _y){
        y = _y;
    }
}
