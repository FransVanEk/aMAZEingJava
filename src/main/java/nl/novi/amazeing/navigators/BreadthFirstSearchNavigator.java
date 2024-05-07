package nl.novi.amazeing.navigators;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.models.position.PositionMetaData;

import java.util.*;

public class  BreadthFirstSearchNavigator {
    private Maze maze;

    public BreadthFirstSearchNavigator(Maze maze) {
        this.maze = maze;
    }

    public List<Instruction> findPathToTarget(int startX, int startY) {
        int[][] distances = new int[maze.getSizeX()][maze.getSizeY()];
        MazePosition targetPosition = null;
        int x=0;
        int y=0;
        for (int[] row : distances) Arrays.fill(row, -1);  // Initialize with -1, meaning unvisited

        Queue<MazePosition> queue = new LinkedList<>();
        MazePosition start = new MazePosition(startX, startY, Orientation.FacingRight);
        queue.add(start);
        distances[startX][startY] = 0;

        while (!queue.isEmpty()) {
            MazePosition current = queue.poll();
            x = current.getPositionX();
            y = current.getPositionY();
            int currentDistance = distances[x][y];

            if (maze.getMetaDataFor(x,y).contains(PositionMetaData.ISTARGET)) {
                distances[x][y] = currentDistance ;
                targetPosition = new MazePosition(x,y,Orientation.FacingRight);
                break;  // Target found
            }

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < maze.getSizeX() && ny >= 0 && ny < maze.getSizeY() && distances[nx][ny] == -1) {
                    Set<PositionMetaData> effects = maze.getMetaDataFor(nx, ny);
                    if (!effects.contains(PositionMetaData.NOENTRY)) {
                        distances[nx][ny] = currentDistance + 1;
                        //we coudl do a check here if it is teh target and clear out the queue or do a break but that needs to be a double one
                        queue.add(new MazePosition(nx, ny, Orientation.FacingRight));  // Orientation can be adjusted based on actual usage
                    }
                }
            }
        }

        return  ConvertToInstructions(reconstructPath(distances,x,y));
    }

        private List<Instruction> ConvertToInstructions(List<MazePosition> mazePositions) {
            List<Instruction> instructions = new ArrayList<>();

            if (mazePositions.size() < 2) {
                return instructions; // No movement needed if there's one or zero positions.
            }

            Orientation currentOrientation = Orientation.FacingRight; // Start facing right.

            for (int i = 0; i < mazePositions.size() - 1; i++) {
                MazePosition current = mazePositions.get(i);
                MazePosition next = mazePositions.get(i + 1);

                // Determine the needed orientation to reach the next position
                Orientation requiredOrientation;
                if (current.getPositionX() == next.getPositionX()) {
                    if (next.getPositionY() > current.getPositionY()) {
                        requiredOrientation = Orientation.FacingDown;
                    } else {
                        requiredOrientation = Orientation.FacingUp;
                    }
                } else {
                    if (next.getPositionX() > current.getPositionX()) {
                        requiredOrientation = Orientation.FacingRight;
                    } else {
                        requiredOrientation = Orientation.FacingLeft;
                    }
                }

                // Add turn instructions if needed
                while (currentOrientation != requiredOrientation) {
                    if ((currentOrientation == Orientation.FacingRight && requiredOrientation == Orientation.FacingDown) ||
                            (currentOrientation == Orientation.FacingDown && requiredOrientation == Orientation.FacingLeft) ||
                            (currentOrientation == Orientation.FacingLeft && requiredOrientation == Orientation.FacingUp) ||
                            (currentOrientation == Orientation.FacingUp && requiredOrientation == Orientation.FacingRight)) {
                        instructions.add(Instruction.TURNRIGHT);
                        currentOrientation = currentOrientation.turnRight();
                    } else {
                        instructions.add(Instruction.TURNLEFT);
                        currentOrientation = currentOrientation.turnLeft();
                    }
                }

                // Finally, move forward
                instructions.add(Instruction.FORWARD);
            }

            return instructions;
        }

    private List<MazePosition> reconstructPath(int[][] distances, int targetX, int targetY) {
        List<MazePosition> path = new ArrayList<>();
        if (distances[targetX][targetY] == -1) return path;  // Target is unreachable

        int x = targetX;
        int y = targetY;
        while (distances[x][y] != 0) {
            path.add(new MazePosition(x, y, Orientation.FacingRight));  // Orientation can be adjusted
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            for (int i = 0; i < 4; i++) {
                int nx = x - dx[i];
                int ny = y - dy[i];
                if (nx >= 0 && nx < maze.getSizeX() && ny >= 0 && ny < maze.getSizeY() &&
                        distances[nx][ny] == distances[x][y] - 1) {
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }
        path.add(new MazePosition(x, y, Orientation.FacingRight));  // Add the start position
        Collections.reverse(path);  // Reverse to start from the beginning
        return path;
    }
}
