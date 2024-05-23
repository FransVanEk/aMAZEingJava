package nl.novi.amazeing.navigators;

import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.models.position.PositionMetaData;

import java.util.*;

public class BreadthFirstSearchNavigator implements Navigator {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private Maze maze;

    @Override
    public List<Instruction> findPathToTarget(Maze maze, int startX, int startY) {
        this.maze = maze;
        int[][] distances = initializeDistances();
        MazePosition targetPosition = performBreadthFirstSearch(distances, startX, startY);
        if (targetPosition == null) {
            return Collections.emptyList();
        }
        List<MazePosition> path = reconstructPath(distances, targetPosition);
        return convertToInstructions(path);
    }

    private int[][] initializeDistances() {
        int[][] distances = new int[maze.getSizeX()][maze.getSizeY()];
        for (int[] row : distances) {
            Arrays.fill(row, -1); // Initialize with -1 (unvisited)
        }
        return distances;
    }

    private MazePosition performBreadthFirstSearch(int[][] distances, int startX, int startY) {
        Queue<MazePosition> queue = new LinkedList<>();
        MazePosition start = new MazePosition(startX, startY, Orientation.FacingRight);
        queue.add(start);
        distances[startX][startY] = 0;

        while (!queue.isEmpty()) {
            MazePosition current = queue.poll();
            int x = current.getPositionX();
            int y = current.getPositionY();
            if (maze.getMetaDataFor(x, y).contains(PositionMetaData.IS_TARGET)) {
                return new MazePosition(x, y, Orientation.FacingRight);
            }
            addNeighborsToQueue(queue, distances, x, y);
        }
        return null; // Target not found
    }

    private void addNeighborsToQueue(Queue<MazePosition> queue, int[][] distances, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if (isUnvisitedPosition(nx, ny, distances) && canEnter(nx, ny)) {
                distances[nx][ny] = distances[x][y] + 1;
                queue.add(new MazePosition(nx, ny, Orientation.FacingRight));
            }
        }
    }

    private boolean isUnvisitedPosition(int x, int y, int[][] distances) {
        return x >= 0 && x < maze.getSizeX() && y >= 0 && y < maze.getSizeY() && distances[x][y] == -1;
    }

    private boolean canEnter(int x, int y) {
        var effects = maze.getMetaDataFor(x, y);
        return maze.isAccessible(effects);
    }

    private List<Instruction> convertToInstructions(List<MazePosition> path) {
        List<Instruction> instructions = new ArrayList<>();
        if (path.size() < 2) return instructions;

        Orientation currentOrientation = Orientation.FacingRight;
        for (int i = 0; i < path.size() - 1; i++) {
            MazePosition current = path.get(i);
            MazePosition next = path.get(i + 1);
            Orientation requiredOrientation = determineRequiredOrientation(current, next);
            instructions.addAll(generateTurnInstructions(currentOrientation, requiredOrientation));
            instructions.add(Instruction.FORWARD);
            currentOrientation = requiredOrientation;
        }
        return instructions;
    }

    private Orientation determineRequiredOrientation(MazePosition current, MazePosition next) {
        if (current.getPositionX() == next.getPositionX()) {
            return next.getPositionY() > current.getPositionY() ? Orientation.FacingDown : Orientation.FacingUp;
        }
        return next.getPositionX() > current.getPositionX() ? Orientation.FacingRight : Orientation.FacingLeft;
    }

    private List<Instruction> generateTurnInstructions(Orientation current, Orientation required) {
        List<Instruction> instructions = new ArrayList<>();
        while (current != required) {
            if (isClockwiseTurn(current, required)) {
                instructions.add(Instruction.TURNRIGHT);
                current = current.turnRight();
            } else {
                instructions.add(Instruction.TURNLEFT);
                current = current.turnLeft();
            }
        }
        return instructions;
    }

    private boolean isClockwiseTurn(Orientation current, Orientation required) {
        return (current == Orientation.FacingRight && required == Orientation.FacingDown) ||
                (current == Orientation.FacingDown && required == Orientation.FacingLeft) ||
                (current == Orientation.FacingLeft && required == Orientation.FacingUp) ||
                (current == Orientation.FacingUp && required == Orientation.FacingRight);
    }

    private List<MazePosition> reconstructPath(int[][] distances, MazePosition target) {
        List<MazePosition> path = new ArrayList<>();
        int x = target.getPositionX();
        int y = target.getPositionY();
        while (distances[x][y] != 0) {
            path.add(new MazePosition(x, y, Orientation.FacingRight));
            int[] previous = findPreviousPosition(distances, x, y);
            x = previous[0];
            y = previous[1];
        }
        path.add(new MazePosition(x, y, Orientation.FacingRight));
        Collections.reverse(path);
        return path;
    }

    private int[] findPreviousPosition(int[][] distances, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x - DX[i];
            int ny = y - DY[i];
            if (isValidPreviousPosition(nx, ny, distances, x, y)) {
                return new int[]{nx, ny};
            }
        }
        return new int[]{x, y};
    }

    private boolean isValidPreviousPosition(int nx, int ny, int[][] distances, int x, int y) {
        return nx >= 0 && nx < maze.getSizeX() && ny >= 0 && ny < maze.getSizeY() &&
                distances[nx][ny] == distances[x][y] - 1;
    }
}
