package nl.novi.amazeing.graphics;

public class GraphicsPosition {

    private final int x;
    private final int y;
    private final int angle;
    private final double elementFactor;

    public GraphicsPosition(int x, int y, int angle, double elementFactor) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.elementFactor = elementFactor;
    }

    public double getElementFactor() {
        return elementFactor;
    }

    public int getAngle() {
        return angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
