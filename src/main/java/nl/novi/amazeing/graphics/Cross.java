package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public class Cross implements Drawable {
    private int factorBase = 60;
    private int armLength = 40;
    private int thickness = 10;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        var armLength = (int) (this.armLength * position.getElementFactor());
        var thickness = (int) (this.thickness * position.getElementFactor());

        int[][] horizontalArm = {
                {-armLength / 2, -thickness / 2}, // Start point
                {armLength / 2, -thickness / 2}, // End point
                {armLength / 2, thickness / 2}, // End point
                {-armLength / 2, thickness / 2} // Start point
        };

        int[][] verticalArm = {
                {-thickness / 2, -armLength / 2}, // Start point
                {thickness / 2, -armLength / 2}, // End point
                {thickness / 2, armLength / 2}, // End point
                {-thickness / 2, armLength / 2} // Start point
        };

        for (int i = 0; i < horizontalArm.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(horizontalArm[i][0], horizontalArm[i][1], 45);
            horizontalArm[i][0] = rotatedPoint.getX() + position.getX();
            horizontalArm[i][1] = rotatedPoint.getY() + position.getY();
        }

        for (int i = 0; i < verticalArm.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(verticalArm[i][0], verticalArm[i][1], 45);
            verticalArm[i][0] = rotatedPoint.getX() + position.getX();
            verticalArm[i][1] = rotatedPoint.getY() + position.getY();
        }

        g2d.setColor(Color.RED);

        // Draw horizontal arm
        g2d.fillPolygon(new int[]{horizontalArm[0][0], horizontalArm[1][0], horizontalArm[2][0], horizontalArm[3][0]},
                new int[]{horizontalArm[0][1], horizontalArm[1][1], horizontalArm[2][1], horizontalArm[3][1]}, 4);

        // Draw vertical arm
        g2d.fillPolygon(new int[]{verticalArm[0][0], verticalArm[1][0], verticalArm[2][0], verticalArm[3][0]},
                new int[]{verticalArm[0][1], verticalArm[1][1], verticalArm[2][1], verticalArm[3][1]}, 4);
    }

    @Override
    public int getFactorBase() {
        return factorBase;
    }
}

