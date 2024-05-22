package nl.novi.amazeing.models;

import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;

import java.util.*;

public class Maze {
    int sizeX;
    int sizeY;

    private final List<MazeElement> mazeElements = new ArrayList<>();

    public Maze(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public List<MazeElement> getMazeElements() {
        return mazeElements;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void addMazeElements(MazeElement mazeElement) {
        mazeElements.add(mazeElement);
    }

    public Set<PositionMetaData> getMetaDataFor(int x, int y) {
        Set<PositionMetaData> uniqueEffects = new HashSet<>();

        for (MazeElement element : mazeElements) {
            if (element.getPosition().isPosition(x, y)) {
                uniqueEffects.addAll(element.getPositionMetaData());
            }
        }
        if(x<0 || x >= sizeX || y <0 || y >=sizeY) {
            uniqueEffects.add(PositionMetaData.NO_TILE);
        }
        return uniqueEffects;
    }

    public Set<PositionMetaData> getMetaDataFor(MazePosition position) {
        return getMetaDataFor(position.getPositionX(),position.getPositionY());
    }

    public void removeElementsAt(int x, int y) {
        for (int i = 0; i < mazeElements.size(); i++) {
            if (mazeElements.get(i).getPosition().isPosition(x, y)) {
                mazeElements.remove(i);
                i--;
            }

        }
    }

    public boolean isEmptySpot(int x, int y) {
        for (MazeElement element : mazeElements) {
            if (element.getPosition().isPosition(x, y)) {
                return false;
            }
        }
        return true;
    }
}

