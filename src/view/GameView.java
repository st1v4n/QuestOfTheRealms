package view;

import model.GameMap;

import java.util.List;
import java.util.Map;

public class GameView {

    public void visualiseMap(List<List<Character>> map, int rows, int cols){
        for(int i = 0;i<rows;++i){
            for(int j=0;j<cols;++j){
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
    }

    public void promptMessage(String message){
        System.out.println(message);
    }

    public void printObjectInformation(Map<String, String> info){
        for(String key : info.keySet()){
            System.out.println(key + ": " + info.get(key));
        }
    }

}
