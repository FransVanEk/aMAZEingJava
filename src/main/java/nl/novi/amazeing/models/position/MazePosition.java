package nl.novi.amazeing.models.position;

import java.util.EnumSet;

public class MazePosition {
    int positionX;
    int positionY;
    Orientation orientation;

    public MazePosition(int positionX, int positionY, Orientation orientation) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    public int getPositionX() {
        return positionX;
    }


    public int getPositionY() {
        return positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public MazePosition MakeStepInCurrentOrientation(int stepsize) {
        switch (orientation) {
            case FacingUp -> positionY -= stepsize;
            case FacingDown -> positionY += stepsize;
            case FacingLeft -> positionX -= stepsize;
            case FacingRight -> positionX += stepsize;
        }
        return this;
    }
    public MazePosition getPositionForCurrentOrientation(int stepsize){
        switch (orientation) {
            case FacingUp ->  {return new MazePosition(positionX,positionY - stepsize, orientation);}
            case FacingDown ->  {return new MazePosition(positionX,positionY + stepsize, orientation);}
            case FacingLeft ->  {return new MazePosition(positionX-stepsize,positionY, orientation);}
            case FacingRight ->  {return new MazePosition(positionX+stepsize,positionY, orientation);}
        }
        return this;
    }

    public MazePosition clone() {
        return new MazePosition(positionX, positionY, orientation);
    }

    public MazePosition turnLeft() {
        orientation = orientation.turnLeft();
        return this;
    }

    public MazePosition turnRight() {
        orientation = orientation.turnRight();
        return this;
    }

    public int getOrientationDegrees() {
        switch (orientation) {
            case FacingRight -> {
                return 90;
            }
            case FacingDown -> {
                return 180;
            }
            case FacingLeft -> {
                return 270;
            }
            case FacingUp -> {
                return 0;
            }
        }
        return 90;
    }

    public boolean isPosition(int x, int y) {
        return (x== this.getPositionX() && y == this.getPositionY());
    }

    public int getCost() {
        return 1;
    }
}
