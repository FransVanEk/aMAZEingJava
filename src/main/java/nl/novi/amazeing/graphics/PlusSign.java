package nl.novi.amazeing.graphics;

import java.awt.*;

public class PlusSign implements Drawable {
    private static final int FACTOR_BASE = 100;
    private static final int ARM_LENGTH = 40;
    private static final int THICKNESS = 10;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        int adjustedArmLength = (int) (ARM_LENGTH * position.elementFactor());
        int adjustedThickness = (int) (THICKNESS * position.elementFactor());

        int[][] horizontalArm = createHorizontalArm(adjustedArmLength, adjustedThickness, position.angle(), position.x(), position.y());
        int[][] verticalArm = createVerticalArm(adjustedArmLength, adjustedThickness, position.angle(), position.x(), position.y());

        g2d.setColor(Color.GREEN);
        drawPolygon(g2d, horizontalArm);
        drawPolygon(g2d, verticalArm);
    }

    private int[][] createHorizontalArm(int armLength, int thickness, int angle, int x, int y) {
        int[][] horizontalArm = {
                {-armLength / 2, -thickness / 2},
                {armLength / 2, -thickness / 2},
                {armLength / 2, thickness / 2},
                {-armLength / 2, thickness / 2}
        };
        return applyRotationAndOffset(horizontalArm,  x, y, angle);
    }

    private int[][] createVerticalArm(int armLength, int thickness, int angle, int x, int y) {
        int[][] verticalArm = {
                {-thickness / 2, -armLength / 2},
                {thickness / 2, -armLength / 2},
                {thickness / 2, armLength / 2},
                {-thickness / 2, armLength / 2}
        };
        return   applyRotationAndOffset(verticalArm, x, y, angle);
    }

    private void drawPolygon(Graphics2D g2d, int[][] points) {
        g2d.fillPolygon(
                new int[]{points[0][0], points[1][0], points[2][0], points[3][0]},
                new int[]{points[0][1], points[1][1], points[2][1], points[3][1]},
                4
        );
    }

    @Override
    public int getFACTOR_BASE() {
        return FACTOR_BASE;
    }
}
