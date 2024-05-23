package nl.novi.amazeing.navigators;

import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;

import java.util.List;

public interface Navigator {
    default List<Instruction> findPathToTarget(Maze maze, MazePosition position) {
        return findPathToTarget(maze,position.getPositionX(), position.getPositionY());
    }

    List<Instruction> findPathToTarget(Maze maze, int startX, int startY);
}
