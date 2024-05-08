package nl.novi.amazeing.graphics;

import java.awt.*;

public class SmileyFace implements Drawable {
    private int factorBase = 50;
    private int headRadius = 40;
    private int eyeRadius = 5;
    private int smileStartAngle = 180;
    private int smileExtentAngle = 180;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        var headRadius = (int) (this.headRadius * position.elementFactor());
        var eyeRadius = (int) (this.eyeRadius * position.elementFactor());

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

        // Draw smile
        g2d.setColor(Color.BLACK);
        g2d.drawArc(headX + headRadius / 4, headY + headRadius / 3, headRadius / 2, headRadius / 4,
                smileStartAngle, smileExtentAngle);
    }

    @Override
    public int getFactorBase() {
        return factorBase;
    }
}
