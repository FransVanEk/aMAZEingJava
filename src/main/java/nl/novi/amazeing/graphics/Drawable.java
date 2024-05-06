package nl.novi.amazeing.graphics;


import java.awt.*;

public interface Drawable {
    void draw(Graphics2D g2d, GraphicsPosition graphicsPosition);
    int getFactorBase();

}
