package nl.novi.amazeing.graphics;

import nl.novi.amazeing.helpers.DrawHelper;

import java.awt.*;

public class OpenDoor implements Drawable {
    private static  final int FACTOR_BASE = 100;
    private static  final int DOOR_WIDTH = 35; // The width of the door
    private static  final int DOOR_HEIGHT = 60; // The height of the door
    private static  final int DOOR_OPEN_ANGLE = 45;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        // Calculate the scaled dimensions of the door
        int scaledDoorWidth = (int) (DOOR_WIDTH * position.elementFactor());
        int scaledDoorHeight = (int) (DOOR_HEIGHT * position.elementFactor());

        // Calculate points for the door
        int[][] doorPoints = {
                {-scaledDoorWidth / 2, -scaledDoorHeight / 2},  // Top left
                {scaledDoorWidth / 2, -scaledDoorHeight / 2},   // Top right
                {scaledDoorWidth / 2, scaledDoorHeight / 2},    // Bottom right
                {-scaledDoorWidth / 2, scaledDoorHeight / 2}    // Bottom left
        };

        applyRotationAndOffset(doorPoints,position.x(), position.y(), 0);

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
        var openDoorEnd = DrawHelper.rotatePointFromOrigin(scaledDoorWidth / 2, 0, position.angle() - DOOR_OPEN_ANGLE);

        g2d.drawLine(doorPoints[1][0], doorPoints[1][1], doorPoints[1][0]+ openDoorEnd.x(), doorPoints[1][1] + openDoorEnd.y());
        g2d.drawLine(doorPoints[1][0]+openDoorEnd.x(), doorPoints[1][1]+openDoorEnd.y(), doorPoints[1][0]+openDoorEnd.x(), doorPoints[2][1]);
        g2d.drawLine(doorPoints[1][0]+openDoorEnd.x(), doorPoints[2][1], doorPoints[1][0], doorPoints[2][1]);
   }

    @Override
    public int getFACTOR_BASE() {
        return FACTOR_BASE;
    }
}