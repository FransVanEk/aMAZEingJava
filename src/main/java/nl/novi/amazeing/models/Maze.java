package nl.novi.amazeing.models;

import nl.novi.amazeing.graphics.Drawable;
import nl.novi.amazeing.graphics.OpenDoor;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.MazeTileDiscoveryEffects;
import nl.novi.amazeing.models.position.Orientation;

import java.util.*;

public class Maze {
    int sizeX;
    int sizeY;
    private MazeTile[][] tiles;

    private List<MazeElement> mazeElements = new ArrayList<>();

    public Maze(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        tiles = new MazeTile[sizeX - 1][sizeY - 1];
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

    public void addMazeElements(int x, int y, Drawable drawableItem, MazeTileDiscoveryEffects... effects) {
        mazeElements.add(new MazeElement(new MazePosition(x, y, Orientation.FacingRight), drawableItem, effects));
    }

    public void addMazeElements(MazeElement mazeElement) {
        mazeElements.add(mazeElement);
    }

    public Set<MazeTileDiscoveryEffects> getEffectsFor(int x, int y) {
        Set<MazeTileDiscoveryEffects> uniqueEffects = new HashSet<>();

        for (MazeElement element : mazeElements) {
            if (element.getPosition().isPosition(x, y)) {
                uniqueEffects.addAll(element.getEffects());
            }
        }
        return uniqueEffects;
    }

    public void RemoveElementsAt(int x, int y) {
        for (int i = 0; i < mazeElements.size(); i++) {
            if (mazeElements.get(i).getPosition().isPosition(x, y)) {
                mazeElements.remove(i);
                i--;
            }

        }
    }
}

