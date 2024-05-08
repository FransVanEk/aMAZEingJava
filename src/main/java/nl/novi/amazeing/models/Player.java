package nl.novi.amazeing.models;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.Triangle;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.navigators.Instruction;

import java.util.Collection;
import java.util.List;

public class Player {
    private final Triangle graphicsPlayer;
    private final Maze maze;
    private final GraphicsRunner graphicsRunner;

    MazePosition mazePosition;

    public Player(Triangle triangle, Maze maze, GraphicsRunner graphicsRunner) {
        this.graphicsPlayer = triangle;
        this.maze = maze;
        this.graphicsRunner = graphicsRunner;
        this.mazePosition = new MazePosition(0, 0, Orientation.FacingRight);
    }


    public Player setPosition(int positionX, int positionY, Orientation orientation) {
        this.mazePosition = new MazePosition(positionX, positionY, orientation);
        return this;
    }

    public void moveForward() {
        MakeStepInCurrentOrientation(1);
    }

    public void moveBackward() {
        MakeStepInCurrentOrientation(-1);
    }

    private void MakeStepInCurrentOrientation(int stepsize) {
        var currentPosition = mazePosition.copy();
        var newPosition = mazePosition.MakeStepInCurrentOrientation(stepsize);
        updateGraphics(currentPosition, newPosition);
    }

    private void updateGraphics(MazePosition currentPosition, MazePosition newPosition) {
        graphicsRunner.performMove(maze, graphicsPlayer, currentPosition, newPosition);
    }

    public void turnLeft() {
        var currentPosition = mazePosition.copy();
        var newPosition = mazePosition.turnLeft();
        updateGraphics(currentPosition, newPosition);
    }

    public void turnRight() {
        var currentPosition = mazePosition.copy();
        var newPosition = mazePosition.turnRight();
        updateGraphics(currentPosition, newPosition);
    }

    public void showMaze() {
        updateGraphics(mazePosition, mazePosition);
    }

    public boolean canMoveForward() {
        return !getEffectsForMovingForward()
                .contains(PositionMetaData.NO_ENTRY);
    }

    public Collection<PositionMetaData> getEffectsForMovingForward() {
        var newPosition = mazePosition.getPositionForCurrentOrientation(+1);
        return maze.getMetaDataFor(newPosition);
    }

    public MazePosition getPosition() {
        return this.mazePosition;
    }

    public void followInstructions(List<Instruction> instructions) {
        for (Instruction action : instructions) {
            switch (action) {
                case FORWARD -> moveForward();
                case BACKWARD -> moveBackward();
                case TURNLEFT -> turnLeft();
                case TURNRIGHT -> turnRight();
            }
        }
    }

    public void setSpeed(int speed) {
        this.graphicsRunner.setSpeed(speed);
    }
}
