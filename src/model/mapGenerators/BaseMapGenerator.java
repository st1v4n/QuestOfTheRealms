package model.mapGenerators;

import model.GameMap;

public interface BaseMapGenerator {

    public static final int MINIMUM_MAP_SIZE = 3;
    public abstract GameMap generateMapFromFile(String fileName);
}
