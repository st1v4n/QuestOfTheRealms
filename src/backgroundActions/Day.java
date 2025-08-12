package backgroundActions;

public class Day {

    private boolean isDay;

    public Day(){
        isDay = true;
    }

    public synchronized void update(){
        if(isDay){
            isDay = false;
        }
        else{
            isDay = true;
        }
    }

    public synchronized boolean isDay(){
        return isDay;
    }

}
