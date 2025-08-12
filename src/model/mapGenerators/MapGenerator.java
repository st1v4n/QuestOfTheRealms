package model.mapGenerators;

import model.GameMap;
import model.gameObjects.GameObject;
import model.playerClasses.Player;
import model.playerClasses.PlayerClass;
import model.terrain.Blank;
import model.terrain.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MapGenerator implements BaseMapGenerator{

    private static GameObject generateObjectBySymbol(char symbol){
        switch(symbol){
            case '*': return new Wall();
            case ' ': return new Blank();
        }
        return null;
    }

    private static List<GameObject> generateRowFromString(String line){
        List<GameObject> result = new ArrayList<>();
        for(int i=0;i<line.length();++i){
            result.add(generateObjectBySymbol(line.charAt(i)));
        }
        return result;
    }

    private static boolean isValid(List<List<GameObject>> map){
        if(map.size() < MINIMUM_MAP_SIZE)return false;
        int expectedLength = map.getFirst().size();
        for(int i = 1;i<map.size();++i){
            if(expectedLength != map.get(i).size())return false;
        }
        return true;
    }

    @Override
    public GameMap generateMapFromFile(String fileName){
        List<List<GameObject>> map = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                map.add(generateRowFromString(line));
            }
            if(isValid(map)){
                Player player = new Player("Ivan", PlayerClass.MAGE);
                return new GameMap(map, player);
            }
            else{
                throw new InputMismatchException("Map is not valid! Must be at least 3x3 and consistent!");
            }
        }
        catch(IOException ioexc){
            System.out.println(ioexc.getMessage());
            System.exit(-1);
        }
        catch(InputMismatchException inputExc){
            System.out.println(inputExc.getMessage());
        }
        return null;
    }
}
