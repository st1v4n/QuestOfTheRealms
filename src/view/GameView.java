package view;

import model.GameMap;

import java.util.List;

public class GameView {

    public static void visualiseMap(GameMap gameMap){
        int rows = gameMap.getYBorder();
        int cols = gameMap.getXBorder();
        List<List<Character>> map = gameMap.getMap();
        for(int i = 0;i<rows;++i){
            for(int j=0;j<cols;++j){
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
    }

}
