package nl.novi.amazeing.graphics;

import java.awt.*;

public class SadFace implements Drawable {
    private static final int FACTOR_BASE = 100;
    private static final int HEAD_RADIUS = 40;
    private static final int EYE_RADIUS = 5;
    private static final int MOUTH_START_ANGLE = 0;
    private static final int MOUTH_EXTENT_ANGLE = 180;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        var headRadius = (int) (HEAD_RADIUS * position.elementFactor());
        var eyeRadius = (int) (EYE_RADIUS * position.elementFactor());

        // Calculate head center position
        int headX = position.x() - headRadius / 2;
        int headY = position.y() - headRadius / 2;

        // Draw head
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(headX, headY, headRadius, headRadius);

        // Calculate eye positions
        int leftEyeX = headX + headRadius / 3;
        int rightEyeX = headX + 2 * headRadius / 3;
        int eyeY = headY + headRadius / 3;

        // Draw eyes
        g2d.setColor(Color.BLACK);
        g2d.fillOval(leftEyeX - eyeRadius / 2, eyeY - eyeRadius / 2, eyeRadius, eyeRadius);
        g2d.fillOval(rightEyeX - eyeRadius / 2, eyeY - eyeRadius / 2, eyeRadius, eyeRadius);

        // Draw mouth
        g2d.setColor(Color.BLACK);
        g2d.drawArc(headX + headRadius / 4, headY + 2 * headRadius / 3, headRadius / 2, headRadius / 4,
                MOUTH_START_ANGLE, MOUTH_EXTENT_ANGLE);
    }

    @Override
    public int getFACTOR_BASE() {
        return FACTOR_BASE;
    }
}

