package nl.novi.amazeing.navigators;

import nl.novi.amazeing.helpers.PositionToInstructionsConverter;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.models.position.PositionMetaData;

import java.util.*;

public class RandomizedSearchNavigator implements Navigator {
    private static final int[][] DIRECTIONS = {
            {1, 0, Orientation.FacingRight.ordinal()},
            {-1, 0, Orientation.FacingLeft.ordinal()},
            {0, 1, Orientation.FacingDown.ordinal()},
            {0, -1, Orientation.FacingUp.ordinal()}
    };
    private Maze maze;
    private final Set<MazePosition> visitedPositions = new HashSet<>();
    private final Map<MazePosition, MazePosition> cameFrom = new HashMap<>();


    public List<Instruction> findPathToTarget(Maze maze,int startX, int startY) {
        this.maze = maze;
        MazePosition targetPosition = exploreRandomly(new MazePosition(startX, startY, Orientation.FacingRight));
        return targetPosition == null ? Collections.emptyList() : PositionToInstructionsConverter.convertToInstructions(reconstructPath(targetPosition));
    }

    private MazePosition exploreRandomly(MazePosition start) {
        Stack<MazePosition> stack = initializeExploration(start);

        while (!stack.isEmpty()) {
            MazePosition current = stack.peek();
            if (isTarget(current)) {
                return current;
            }

            List<MazePosition> neighbors = getUnvisitedNeighbors(current);
            if (neighbors.isEmpty()) {
                stack.pop();
            } else {
                stack.push(visitNextNeighbor(neighbors, current));
            }
        }

        return null; // Target not found
    }

    private Stack<MazePosition> initializeExploration(MazePosition start) {
        Stack<MazePosition> stack = new Stack<>();
        stack.push(start);
        visitedPositions.add(start);
        return stack;
    }

    private boolean isTarget(MazePosition position) {
        return maze.getMetaDataFor(position.getPositionX(), position.getPositionY()).contains(PositionMetaData.IS_TARGET);
    }

    private MazePosition visitNextNeighbor(List<MazePosition> neighbors, MazePosition current) {
        Collections.shuffle(neighbors);
        MazePosition next = neighbors.get(0);
        visitedPositions.add(next);
        cameFrom.put(next, current);
        return next;
    }

    private List<MazePosition> getUnvisitedNeighbors(MazePosition position) {
        List<MazePosition> neighbors = new ArrayList<>();
        int x = position.getPositionX();
        int y = position.getPositionY();

        for (int[] direction : DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            Orientation orientation = Orientation.values()[direction[2]];
            MazePosition neighbor = new MazePosition(nx, ny, orientation);

            if (isValidNeighbor(nx, ny, neighbor)) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    private boolean isValidNeighbor(int nx, int ny, MazePosition neighbor) {
        return nx >= 0 && nx < maze.getSizeX() &&
                ny >= 0 && ny < maze.getSizeY() &&
                !visitedPositions.contains(neighbor) &&
                !maze.isDeadly(neighbor) &&
                maze.isAccessible(neighbor)
                ;
    }

    private List<MazePosition> reconstructPath(MazePosition target) {
        List<MazePosition> path = new ArrayList<>();
        MazePosition current = target;

        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
