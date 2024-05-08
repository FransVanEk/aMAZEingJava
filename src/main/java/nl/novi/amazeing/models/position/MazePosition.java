package nl.novi.amazeing.models.position;

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

    public MazePosition MakeStepInCurrentOrientation(int stepSize) {
        switch (orientation) {
            case FacingUp -> positionY -= stepSize;
            case FacingDown -> positionY += stepSize;
            case FacingLeft -> positionX -= stepSize;
            case FacingRight -> positionX += stepSize;
        }
        return this;
    }
    public MazePosition getPositionForCurrentOrientation(int stepSize){
        switch (orientation) {
            case FacingUp ->  {return new MazePosition(positionX,positionY - stepSize, orientation);}
            case FacingDown ->  {return new MazePosition(positionX,positionY + stepSize, orientation);}
            case FacingLeft ->  {return new MazePosition(positionX- stepSize,positionY, orientation);}
            case FacingRight ->  {return new MazePosition(positionX+ stepSize,positionY, orientation);}
        }
        return this;
    }

    public MazePosition copy() {
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


}
