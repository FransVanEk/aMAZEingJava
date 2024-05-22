package nl.novi.amazeing.models;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.Triangle;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.navigators.Instruction;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Player {
    private final Triangle graphicsPlayer;
    private final Maze maze;
    private final GraphicsRunner graphicsRunner;

    MazePosition mazePosition;
    private String name = "not set";

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
        System.out.println("Good luck, " + name);
        updateGraphics(mazePosition, mazePosition);
    }

    public boolean canMoveForward() {
        Collection<PositionMetaData> effects = getEffectsForMovingForward();
        return canMove(effects);
    }

    public boolean canMoveLeft() {
        Collection<PositionMetaData> effects = getEffectsForMovingLeft();
        return canMove(effects);
    }

    public boolean canMoveRight() {
        Collection<PositionMetaData> effects = getEffectsForMovingRight();
        return canMove(effects);
    }

    private boolean canMove(Collection<PositionMetaData> effects) {
        return !(effects.contains(PositionMetaData.NO_ENTRY) || effects.contains(PositionMetaData.NO_TILE));
    }

    public boolean isSaveToMoveForward() {
        Collection<PositionMetaData> effects = getEffectsForMovingForward();
        if(!canMove(effects)){ return false;}
        return !(effects.contains(PositionMetaData.IS_DEADLY));
    }

    public boolean isSaveToMoveLeft() {
        Collection<PositionMetaData> effects = getEffectsForMovingLeft();
        if(!canMove(effects)){ return false;}
        return !(effects.contains(PositionMetaData.IS_DEADLY));
    }

    public boolean isSaveToMoveRight() {
        Collection<PositionMetaData> effects = getEffectsForMovingRight();
        if(!canMove(effects)){ return false;}
        return !(effects.contains(PositionMetaData.IS_DEADLY));
    }

    public Collection<PositionMetaData> getEffectsForMovingForward() {
        var newPosition = mazePosition.getPositionForCurrentOrientationForStep(+1);
        return maze.getMetaDataFor(newPosition);
    }

    public Collection<PositionMetaData> getEffectsForMovingLeft() {
        var newPosition = mazePosition.getPositionForStep(+1, mazePosition.getOrientation().turnLeft());
        return maze.getMetaDataFor(newPosition);
    }

    public Collection<PositionMetaData> getEffectsForMovingRight() {
        var newPosition = mazePosition.getPositionForStep(+1,  mazePosition.getOrientation().turnRight());
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

    public void followInstructions(Instruction[] instructions) {
        followInstructions(Arrays.stream(instructions).toList());
    }

    public void setSpeed(int speed) {
        this.graphicsRunner.setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public void setName(String spelerNaam) {
        this.name = spelerNaam;
    }
}
