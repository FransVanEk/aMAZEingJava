package nl.novi.amazeing.navigators;

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
        return targetPosition == null ? Collections.emptyList() : convertToInstructions(reconstructPath(targetPosition));
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
                !maze.getMetaDataFor(nx, ny).contains(PositionMetaData.NO_ENTRY);
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
}
