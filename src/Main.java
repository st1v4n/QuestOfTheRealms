import view.GameMap;


public class Main {
    public static void main(String[] args) {

            try{
                GameMap gm = new GameMap("src/view/mainMap.txt");
                gm.visualiseMap();
            }
            catch(Exception e){
                System.out.println("goodbye world");
            }
        }

}