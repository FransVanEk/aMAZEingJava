package nl.novi.amazeing.navigators;

import nl.novi.amazeing.helpers.PositionToInstructionsConverter;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;

import java.util.*;

public class SimpleNavigator implements Navigator {
    private Maze maze;
    private boolean[][] visited;
    private final List<MazePosition> path = new ArrayList<>();

    @Override
    public List<Instruction> findPathToTarget(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.visited = new boolean[maze.getSizeX()][maze.getSizeY()];
        MazePosition startPosition = new MazePosition(startX, startY, Orientation.FacingRight);
        markPositionAsVisited(startPosition);

        while (!maze.isTarget(getCurrentPosition())) {
            if (!moveToNextPosition()) {
                backtrack();
            }
        }

        return PositionToInstructionsConverter.convertToInstructions(path);
    }

    private boolean moveToNextPosition() {
        for (MazePosition nextPosition : getAdjacentPositions(getCurrentPosition())) {
            if (isPositionValid(nextPosition)) {
                markPositionAsVisited(nextPosition);
                return true;
            }
        }
        return false;
    }

    private void backtrack() {
        if (path.size() > 1) {
            path.remove(path.size() - 1);
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Maze has no solution");
        }
    }

    private MazePosition getCurrentPosition() {
        return path.get(path.size() - 1);
    }

    private List<MazePosition> getAdjacentPositions(MazePosition position) {
        return Arrays.asList(
                new MazePosition(position.getPositionX() + 1, position.getPositionY(), Orientation.FacingRight),
                new MazePosition(position.getPositionX(), position.getPositionY() + 1, Orientation.FacingRight),
                new MazePosition(position.getPositionX(), position.getPositionY() - 1, Orientation.FacingRight),
                new MazePosition(position.getPositionX() - 1, position.getPositionY(), Orientation.FacingRight)
        );
    }

    private boolean isPositionValid(MazePosition position) {
        return maze.isAccessible(position) && !maze.isDeadly(position) && !isVisited(position);
    }

    private boolean isVisited(MazePosition position) {
        return visited[position.getPositionX()][position.getPositionY()];
    }

    private void markPositionAsVisited(MazePosition position) {
        path.add(position);
        visited[position.getPositionX()][position.getPositionY()] = true;
    }
}
