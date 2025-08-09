package model;

import model.enemy.Enemy;
import model.playerClasses.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameMap {

    public static final int MINIMUM_MAP_SIZE = 3;
    private List<List<Character>> map;
    private final Player player;
    private Map<Coordinates, Enemy> enemies;

    private boolean isValid(List<List<Character>> _map){
        if(_map.size() < MINIMUM_MAP_SIZE){
            return false;
        }
        int length = _map.getFirst().size();
        for(int i = 0;i<_map.size();++i){
            if(_map.get(i).size() != length)return false;
        }
        return true;
    }

    private void setBorders(List<List<Character>> _map){
        if(!isValid(_map)){
            this.map = new ArrayList<>();
            throw new IllegalArgumentException("Invalid map format (must be at least 3x3 and consistent)!");
        }
    }

    private void construct(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                List<Character> currentRow = new ArrayList<>();
                for(int i=0;i<line.length();++i){
                    currentRow.add(line.charAt(i));
                }
                map.add(currentRow);
            }
            setBorders(map);
        }
        catch(IOException ioexc){
            throw new IllegalArgumentException("An error occured while trying to read from your file! Please, Try again!");
        }
    }

    private void setEntityCharacters(){
        int row = player.getPosition().getY();
        int col = player.getPosition().getX();
        map.get(row).set(col, player.getMapVisualisation());
        for(Coordinates cord : enemies.keySet()){
            row = cord.getY();
            col = cord.getX();
            map.get(row).set(col, enemies.get(cord).getMapVisualisation());
        }
    }

    public GameMap(String fileName, Player _player, Map<Coordinates, Enemy> _enemies){
        map = new ArrayList<>();
        construct(fileName);
        player = _player;
        enemies = _enemies;
        setEntityCharacters();
    }

    public int getYBorder(){
        return map.size();
    }

    public int getXBorder(){
        return map.getFirst().size();
    }

    public List<List<Character>> getMap(){
        return map;
    }

    public Player getPlayer(){
        return player;
    }

    public Map<Coordinates, Enemy> getEnemies(){
        return enemies;
    }

}
