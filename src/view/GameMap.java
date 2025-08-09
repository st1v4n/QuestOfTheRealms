package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private List<String> map;
    public static final int X_BORDER_RIGHT = 20;
    public static final int Y_BORDER_DOWN = 16;
    public static final int X_BORDER_LEFT = 1;
    public static final int Y_BORDER_UP = 1;

    private void construct(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                map.add(line);
            }
        }
        catch(IOException ioexc){
            System.out.println("Your file could not be read properly! Please, try with different one!");
            throw new IllegalArgumentException("An error occured while trying to read from your file! Please, Try again!");
        }
    }

    public GameMap(String fileName){
        map = new ArrayList<>();
        construct(fileName);
    }

    public GameMap(List<String> _map){
        if(_map.size() != Y_BORDER_DOWN + Y_BORDER_UP || _map.get(0).length() != X_BORDER_RIGHT + X_BORDER_LEFT){
            throw new IllegalArgumentException("Your input map doesnt match the requirements! 17 x 21");
        }
        map = _map;
    }
    public void visualiseMap(){
        for(int i=0;i<map.size();++i){
            System.out.println(map.get(i));
        }
    }
}
