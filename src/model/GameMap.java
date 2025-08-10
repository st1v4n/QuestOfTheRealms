package model;

import items.Item;
import model.enemy.Enemy;
import model.playerClasses.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameMap {

    public static final int MINIMUM_MAP_SIZE = 3;
    private List<List<Character>> map;
    private final Player player;
    private final Map<Coordinates, Enemy> enemies;
    private final Map<Coordinates, Item> items;

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
        int row = player.getRow();
        int col = player.getColumn();
        map.get(row).set(col, player.getMapVisualisation());
        for(Coordinates cord : enemies.keySet()){
            row = cord.getRow();
            col = cord.getColumn();
            map.get(row).set(col, enemies.get(cord).getMapVisualisation());
        }
        for(Coordinates cord : items.keySet()){
            row = cord.getRow();
            col = cord.getColumn();
            map.get(row).set(col, items.get(cord).getMapVisualisation());
        }
    }

    public GameMap(String fileName, Player _player, Map<Coordinates, Enemy> _enemies, Map<Coordinates, Item> _items){
        map = new ArrayList<>();
        construct(fileName);
        player = _player;
        enemies = _enemies;
        items = _items;
        setEntityCharacters();
    }

    public int getYBorder(){
        return map.size();
    }

    public int getXBorder(){
        return map.getFirst().size();
    }

    public List<List<Character>> getMap(){
        return Collections.unmodifiableList(map);
    }

    public Enemy getEnemyAtPosition(Coordinates position){
        return enemies.get(position);
    }

    public void performPlayerAttackOn(Enemy enemy){
        player.attack(enemy);
    }

    public synchronized String movePlayer(int rowAddition, int columnAddition){
        int potentialRow = player.getRow() + rowAddition;
        int potentialColumn = player.getColumn() + columnAddition;
        char symbol = map.get(potentialRow).get(potentialColumn);
        if(symbol != '*'){
            String actionResult = "";
            Coordinates newPosition = new Coordinates(potentialRow, potentialColumn);
            if(symbol >= 'A' && symbol <= 'Z'){
                Enemy enemy = enemies.get(newPosition);
                player.attack(enemy);
                if(!enemy.isAlive()){
                    enemies.remove(newPosition);
                    actionResult += "Enemy Killed! \n";
                }
                else{
                    return "Attacked enemy " + enemy.toString();
                }
            }
            actionResult += "Player moved successfully!";
            map.get(player.getRow()).set(player.getColumn(), ' ');
            player.move(potentialRow, potentialColumn);
            map.get(potentialRow).set(potentialColumn, player.getMapVisualisation());
            if(items.containsKey(newPosition)){
                player.addItemToInventory(items.get(newPosition));
                actionResult += "\nPicked up item: " + items.get(newPosition).toString();
                items.remove(newPosition);
            }
            return actionResult;
        }
        else{
            return "There is an obstacle on the map and moving is not allowed!";
        }
    }

    public String getPlayerInfo(){
        return "Class: " + player.getPlayerClass() + " \n"
                + "Health: " + player.getHealth() + "\n"
                + "Mana: " + player.getMana() + "\n"
                + "Attack: " + player.getAttack() + "\n"
                + "Defense: " + player.getDefense() + "\n"
                + player.getInventoryContent();
    }

}
