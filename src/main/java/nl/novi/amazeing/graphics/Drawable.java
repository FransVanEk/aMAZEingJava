package nl.novi.amazeing.graphics;


import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public interface Drawable {
    void draw(Graphics2D g2d, GraphicsPosition graphicsPosition);

    default int[][] applyRotationAndOffset(int[][] points, int offsetX, int offsetY, int angle) {
        for (int i = 0; i < points.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(points[i][0], points[i][1], angle);
            points[i][0] = rotatedPoint.x() + offsetX;
            points[i][1] = rotatedPoint.y() + offsetY;
        }
        return points;
    }

    int getFACTOR_BASE();
}
