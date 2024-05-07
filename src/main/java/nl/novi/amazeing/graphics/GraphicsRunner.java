package nl.novi.amazeing.graphics;

import nl.novi.amazeing.exceptions.SteppedOnDeadlyElementException;
import nl.novi.amazeing.helpers.DrawHelper;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class GraphicsRunner extends JPanel {
    private BufferedImage mazeImage;

    private int NUM_STEPS = 6;
    private double blockSize = 100;
    private int frameWidth;
    private int frameHeight;
    private Maze maze;

    public GraphicsRunner() {
        this.start();
    }

    public void start() {
        JFrame frame = new JFrame("Amazing maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get all screens
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();



        Rectangle bounds = screens[screens.length - 1].getDefaultConfiguration().getBounds();

        frame.setSize(bounds.width/2, bounds.height);
        frame.setLocation(bounds.x, bounds.y);

        frame.add(this);
        activateResizeLogic(frame);
        frame.setVisible(true);
    }

    private void activateResizeLogic(JFrame frame) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frameWidth = frame.getWidth();
                frameHeight = frame.getHeight() - 30;
                if(maze!=null) {
                    initMazeImage(maze);
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.drawImage(mazeImage, 0, 0, null);
                }
            }
        });
    }

    public void performMove(Maze maze, Triangle graphicsPlayer, MazePosition currentPosition, MazePosition newPosition) throws SteppedOnDeadlyElementException {
        blockSize = calculateBlockSize(maze);
        initMazeImage(maze);
        // Calculate step sizes for each axis
        Graphics2D g2d = (Graphics2D) getGraphics();
        double stepX = (newPosition.getPositionX() - currentPosition.getPositionX()) / (double) NUM_STEPS;
        double stepY = (newPosition.getPositionY() - currentPosition.getPositionY()) / (double) NUM_STEPS;
        double stepAngle = DrawHelper.getRotationStepAngle(currentPosition.getOrientationDegrees(), newPosition.getOrientationDegrees(), NUM_STEPS);
        // Interpolate positions and draw at each step
        for (int i = 0; i < NUM_STEPS; i++) {
            double interpolatedX = currentPosition.getPositionX() + (i + 1) * stepX + 0.5;
            double interpolatedY = currentPosition.getPositionY() + (i + 1) * stepY + 0.5;
            double interpolatedAngle = currentPosition.getOrientationDegrees() +( i * stepAngle);
           //  g2d = (Graphics2D) getGraphics();
            // Draw the graphics at the interpolated position
            g2d.drawImage(mazeImage, 0, 0, null);
            drawGraphicsAtPosition(g2d,  graphicsPlayer, (int)(interpolatedX * blockSize ), (int) (interpolatedY * blockSize),(int) interpolatedAngle );

            // Sleep for a short duration to visualize the movement
            try {
                Thread.sleep(20); // Adjust sleep duration as needed
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

        g2d.drawImage(mazeImage, 0, 0, null);
        drawGraphicsAtPosition(g2d, graphicsPlayer, (int) ((newPosition.getPositionX() + 0.5) * blockSize), (int) ((newPosition.getPositionY() + 0.5) * blockSize), (int) newPosition.getOrientationDegrees());

        var effects = maze.getMetaDataFor(newPosition);
        if(effects.contains(PositionMetaData.ISTARGET)) {
            drawGraphicsAtPosition(g2d, new SmileyFace(), (int) ((newPosition.getPositionX() + 0.5) * blockSize), (int) ((newPosition.getPositionY() + 0.5) * blockSize), (int) newPosition.getOrientationDegrees());
        }
        if(effects.contains(PositionMetaData.ISBONUS)) {
            maze.RemoveElementsAt(newPosition.getPositionX(),newPosition.getPositionY());
            mazeImage= null;
        }

        if(effects.contains(PositionMetaData.ISDEADLY)) {
            drawGraphicsAtPosition(g2d, new SadFace(), (int) ((newPosition.getPositionX() + 0.5) * blockSize), (int) ((newPosition.getPositionY() + 0.5) * blockSize), (int) newPosition.getOrientationDegrees());
            throw new SteppedOnDeadlyElementException("Oooops");
        }
            this.maze = maze;
    }

    private void initMazeImage(Maze maze) {
        if (mazeImage == null || mazeImage.getWidth() != frameWidth || mazeImage.getHeight() != frameHeight) {
            mazeImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mazeImage.createGraphics();
            drawMaze(g2d, maze);
            g2d.dispose();
        }
    }

    private void drawMaze(Graphics2D g2d, Maze maze) {
        for(int x = 0; x < maze.getSizeX(); x++) {
            for(int y = 0; y < maze.getSizeY(); y++) {
                drawItemAt(g2d, x, y,new Tile());
            }
        }
        drawItemsOnMaze(g2d,maze);
    }

    private void drawItemsOnMaze(Graphics2D g2d, Maze maze) {
        for(MazeElement item: maze.getMazeElements()){
            drawItemAt(g2d,item.getPosition().getPositionX(), item.getPosition().getPositionY() ,item.getItem());
        }
    }

    private void drawItemAt(Graphics2D g2d, int x, int y, Drawable itemToDraw) {
        var factor = blockSize/ (double)itemToDraw.getFactorBase();
        itemToDraw.draw(g2d, new GraphicsPosition((int)((x +.5) * blockSize),(int) ((y+0.5)*blockSize),0,factor ));
    }


    private double calculateBlockSize(Maze maze) {
        return Math.min(frameWidth/(double)maze.getSizeX(),(frameHeight-30)/(double)maze.getSizeY())*0.9;
    }

    // Draw graphics at a specified position
    private void drawGraphicsAtPosition( Graphics2D g2d,Drawable graphicsPlayer, int x, int y,int angle) {
        var factor = blockSize/ (double)graphicsPlayer.getFactorBase();
        graphicsPlayer.draw(g2d, new GraphicsPosition(x, y, angle, factor));
    }


}
