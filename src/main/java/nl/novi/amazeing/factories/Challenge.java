package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.Cross;
import nl.novi.amazeing.graphics.Drawable;
import nl.novi.amazeing.graphics.SadFace;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.Player;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.models.position.PositionMetaData;

public record Challenge(Maze maze, Player player) {

    public void addMazeItem(int x, int y, Orientation orientation, Drawable item, PositionMetaData positionMetaData) {
        var position = new MazePosition(x,y, orientation);
        var element = new MazeElement(position, item, positionMetaData);
        maze().addMazeElement(element);
    }
}
