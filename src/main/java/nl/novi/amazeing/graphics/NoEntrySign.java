package nl.novi.amazeing.graphics;

import java.awt.*;

public class NoEntrySign implements Drawable {
    private int factorBase = 60;
    private int circleRadius = 40;
    private int barWidth = 10;

    @Override
    public void draw(Graphics2D g2d, GraphicsPosition position) {
        var circleRadius = (int) (this.circleRadius * position.getElementFactor());
        var barWidth = (int) (this.barWidth * position.getElementFactor());

        // Calculate circle center position
        int centerX = position.getX() - circleRadius / 2;
        int centerY = position.getY() - circleRadius / 2;

        // Draw red circle
        g2d.setColor(Color.RED);
        g2d.fillOval(centerX, centerY, circleRadius, circleRadius);

        // Calculate bar position
        int barY = position.getY() - barWidth / 2;

        // Draw white bar
        g2d.setColor(Color.WHITE);
        g2d.fillRect(centerX, barY, circleRadius, barWidth);
    }

    @Override
    public int getFactorBase() {
        return factorBase;
    }
}

