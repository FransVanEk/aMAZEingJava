package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public class Triangle implements Drawable {
    private int factorBase = 100;
    private int baseLength = 40;
    private int height = 60;

    public void draw(Graphics2D g2d, GraphicsPosition position) {

        var height =(int) (this.height * position.elementFactor());
        var baseLength = (int) (this.baseLength * position.elementFactor());

        int[][] points = {
                {0, -height / 2}, // Top point
                {-baseLength / 2, height / 2}, // Bottom left
                {baseLength / 2, height / 2} // Bottom right
        };

        for (int i = 0; i < points.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(points[i][0], points[i][1], position.angle());
            points[i][0] = rotatedPoint.getX() + position.x();
            points[i][1] = rotatedPoint.getY() + position.y();
        }
        g2d.setColor(Color.BLUE);

        // Draw the triangle
        g2d.fillPolygon(new int[]{points[0][0], points[1][0], points[2][0]},
                new int[]{points[0][1], points[1][1], points[2][1]}, 3);
    }

    public int getFactorBase() {
        return factorBase;
    }
}

