package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public class OpenDoor implements Drawable {
    private final int factorBase = 100;
    private int doorWidth = 35; // The width of the door
    private int doorHeight = 60; // The height of the door
    private int doorOpenAngle = 45;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        // Calculate the scaled dimensions of the door
        int scaledDoorWidth = (int) (this.doorWidth * position.elementFactor());
        int scaledDoorHeight = (int) (this.doorHeight * position.elementFactor());

        // Calculate points for the door
        int[][] doorPoints = {
                {-scaledDoorWidth / 2, -scaledDoorHeight / 2},  // Top left
                {scaledDoorWidth / 2, -scaledDoorHeight / 2},   // Top right
                {scaledDoorWidth / 2, scaledDoorHeight / 2},    // Bottom right
                {-scaledDoorWidth / 2, scaledDoorHeight / 2}    // Bottom left
        };

        // Rotate and translate points
        for (int i = 0; i < doorPoints.length; i++) {
            var rotatedPoint = DrawHelper.rotatePointFromOrigin(doorPoints[i][0], doorPoints[i][1], position.angle());
            doorPoints[i][0] = rotatedPoint.getX() + position.x();
            doorPoints[i][1] = rotatedPoint.getY() + position.y();
        }

        // Draw the door
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillPolygon(new int[]{doorPoints[0][0], doorPoints[1][0], doorPoints[2][0], doorPoints[3][0]},
                new int[]{doorPoints[0][1], doorPoints[1][1], doorPoints[2][1], doorPoints[3][1]}, 4);

        // Draw the door frame
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawPolygon(new int[]{doorPoints[0][0], doorPoints[1][0], doorPoints[2][0], doorPoints[3][0]},
                new int[]{doorPoints[0][1], doorPoints[1][1], doorPoints[2][1], doorPoints[3][1]}, 4);

        // Calculate and draw the open part of the door
        var openDoorEnd = DrawHelper.rotatePointFromOrigin(scaledDoorWidth / 2, 0, position.angle() - doorOpenAngle);

        g2d.drawLine(doorPoints[1][0], doorPoints[1][1], doorPoints[1][0]+ openDoorEnd.getX(), doorPoints[1][1] + openDoorEnd.getY());
        g2d.drawLine(doorPoints[1][0]+openDoorEnd.getX(), doorPoints[1][1]+openDoorEnd.getY(), doorPoints[1][0]+openDoorEnd.getX(), doorPoints[2][1]);
        g2d.drawLine(doorPoints[1][0]+openDoorEnd.getX(), doorPoints[2][1], doorPoints[1][0], doorPoints[2][1]);
   }

    @Override
    public int getFactorBase() {
        return factorBase;
    }
}