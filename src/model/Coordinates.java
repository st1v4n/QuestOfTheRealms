package model;

import java.util.Objects;

public class Coordinates {
    private int row;
    private int column;
    public Coordinates(int _row, int _column){
        row = _row;
        column = _column;
    }
    @Override
    public boolean equals(Object other){
        if(this==other)return true;
        if(other == null || this.getClass() != other.getClass())return false;
        Coordinates help = (Coordinates) other;
        return row == help.row && column == help.column;
    }
    @Override
    public int hashCode(){
        return Objects.hash(row, column);
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public void setRow(int _row){
        row = _row;
    }
    public void setColumn(int _column){
        column = _column;
    }
}
