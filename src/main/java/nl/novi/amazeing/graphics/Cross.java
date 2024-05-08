package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public class Cross implements Drawable {
    private final int factorBase = 60;
    private final int armLength = 40;
    private final int thickness = 10;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        int adjustedArmLength = (int) (armLength * position.elementFactor());
        int adjustedThickness = (int) (thickness * position.elementFactor());

        int[][] horizontalArm = createHorizontalArm(adjustedArmLength, adjustedThickness, 45, position.x(), position.y());
        int[][] verticalArm = createVerticalArm(adjustedArmLength, adjustedThickness, 45, position.x(), position.y());

        g2d.setColor(Color.RED);
        drawPolygon(g2d, horizontalArm);
        drawPolygon(g2d, verticalArm);
    }

    private int[][] createHorizontalArm(int armLength, int thickness, int angle, int x, int y) {
        int[][] horizontalArm = {{-armLength / 2, -thickness / 2}, {armLength / 2, -thickness / 2}, {armLength / 2, thickness / 2}, {-armLength / 2, thickness / 2}};
        return applyRotationAndOffset(horizontalArm, angle, x, y);
    }

    private int[][] createVerticalArm(int armLength, int thickness, int angle, int x, int y) {
        int[][] verticalArm = {{-thickness / 2, -armLength / 2}, {thickness / 2, -armLength / 2}, {thickness / 2, armLength / 2}, {-thickness / 2, armLength / 2}};
        return applyRotationAndOffset(verticalArm, angle, x, y);
    }

    private int[][] applyRotationAndOffset(int[][] points, int angle, int offsetX, int offsetY) {
        for (int i = 0; i < points.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(points[i][0], points[i][1], angle);
            points[i][0] = rotatedPoint.getX() + offsetX;
            points[i][1] = rotatedPoint.getY() + offsetY;
        }
        return points;
    }

    private void drawPolygon(Graphics2D g2d, int[][] points) {
        g2d.fillPolygon(new int[]{points[0][0], points[1][0], points[2][0], points[3][0]}, new int[]{points[0][1], points[1][1], points[2][1], points[3][1]}, 4);
    }

    @Override
    public int getFactorBase() {
        return factorBase;
    }
}
