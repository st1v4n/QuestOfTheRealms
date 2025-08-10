package view;

import model.GameMap;

import java.util.List;

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

}
