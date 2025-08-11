package view.visualisators;

import java.util.HashMap;
import java.util.Map;

public class ConsoleVisualisator implements BaseVisualisator{
    private final Map<String, Character> visualisations;

    public ConsoleVisualisator(){
        visualisations = new HashMap<>();
        visualisations.put("Player", '@');
        visualisations.put("Bandit", 'B');
        visualisations.put("Monster", 'M');
        visualisations.put("Boss", 'S');
        visualisations.put("Potion", 'p');
        visualisations.put("Weapon", 'w');
        visualisations.put("Armor", 'a');
        visualisations.put("Wall", '*');
        visualisations.put("Blank", ' ');
    }

    @Override
    public Object getVisualisation(String obj){
        return visualisations.get(obj);
    }
}
