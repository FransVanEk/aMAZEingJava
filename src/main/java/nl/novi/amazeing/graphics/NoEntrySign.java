package nl.novi.amazeing.graphics;

import java.awt.*;

public class NoEntrySign implements Drawable {
    private static final int FACTOR_BASE = 60;
    private static final int CIRCLE_RADIUS = 40;
    private static final int BAR_HEIGHT = 8;
 final
    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        var circleRadius = (int) (CIRCLE_RADIUS * position.elementFactor());
        var barSize = (int) (BAR_HEIGHT * position.elementFactor());

        // Calculate circle center position
        int centerX = position.x() - circleRadius / 2;
        int centerY = position.y() - circleRadius / 2;

        // Draw red circle
        g2d.setColor(Color.RED);
        g2d.fillOval(centerX, centerY, circleRadius, circleRadius);

        // Calculate bar position
        int barY = position.y() - barSize / 2;

        // Draw white bar
        g2d.setColor(Color.WHITE);
        g2d.fillRect(centerX, barY, circleRadius, barSize);
    }

    @Override
    public int getFACTOR_BASE() {
        return FACTOR_BASE;
    }
}

