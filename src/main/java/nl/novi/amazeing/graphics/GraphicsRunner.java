package nl.novi.amazeing.graphics;

import nl.novi.amazeing.exceptions.SteppedOnDeadlyElementException;
import nl.novi.amazeing.helpers.DrawHelper;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.position.MazePosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GraphicsRunner extends JPanel implements KeyListener {
    private BufferedImage mazeImage;
    private int NUM_STEPS = 10;
    private double blockSize = 100;
    private int frameWidth;
    private int frameHeight;
    private Maze maze;
    private Drawable graphicsPlayer;
    private MazePosition playerPosition;

    public GraphicsRunner() {
        this.start();
    }

    public void setSpeed(long speed) {
        NUM_STEPS = 5 + (int)(100/speed);
    }

    public void start() {
        JFrame frame = createFrame();
        activateResizeLogic(frame);
        addKeyListeners(frame);
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Amazing maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle bounds = getLastScreenBounds();
        frame.setSize(bounds.width / 2, bounds.height);
        frame.setLocation(bounds.x, bounds.y);
        frame.add(this);
        return frame;
    }

    private Rectangle getLastScreenBounds() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        return screens[screens.length - 1].getDefaultConfiguration().getBounds();
    }

    private void activateResizeLogic(JFrame frame) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onFrameResize(frame);
            }
        });
        frame.addWindowStateListener( e -> onFrameResize(frame));
    }

    private void onFrameResize(JFrame frame) {
        frameWidth = (frame.getWidth());
        frameHeight = (frame.getHeight()) - 30;
        if (maze != null) {
            updateMazeImage();
        }
    }

    private void updateMazeImage() {
        initMazeImage(maze);
        Graphics2D g2d = (Graphics2D) getGraphics();
        drawCachedMaze(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (mazeImage != null) {
            drawCachedMaze(g2d);
        }
    }

    public void performMove(Maze maze, Drawable graphicsPlayer, MazePosition currentPosition, MazePosition newPosition) throws SteppedOnDeadlyElementException {
        initMazeImage(maze);
        interpolateMove(graphicsPlayer, currentPosition, newPosition);
        this.maze = maze;
        this.graphicsPlayer = graphicsPlayer;
        this.playerPosition = newPosition;
    }

    private void interpolateMove(Drawable graphicsPlayer, MazePosition currentPosition, MazePosition newPosition) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        double stepX = (newPosition.getPositionX() - currentPosition.getPositionX()) / (double) NUM_STEPS;
        double stepY = (newPosition.getPositionY() - currentPosition.getPositionY()) / (double) NUM_STEPS;
        double stepAngle = DrawHelper.getRotationStepAngle(currentPosition.getOrientationDegrees(), newPosition.getOrientationDegrees(), NUM_STEPS);

        for (int i = 0; i < NUM_STEPS; i++) {
            double interpolatedX = currentPosition.getPositionX() + (i + 1) * stepX + 0.5;
            double interpolatedY = currentPosition.getPositionY() + (i + 1) * stepY + 0.5;
            double interpolatedAngle = currentPosition.getOrientationDegrees() + (i * stepAngle);
            drawInterpolatedMove(g2d, graphicsPlayer, interpolatedX, interpolatedY, interpolatedAngle);
        }
        drawPlayer(graphicsPlayer, newPosition, g2d);
    }

    private void drawInterpolatedMove(Graphics2D g2d, Drawable graphicsPlayer, double interpolatedX, double interpolatedY, double interpolatedAngle) {
        g2d.drawImage(mazeImage, 0, 0, null);
        drawGraphicsAtPosition(g2d, graphicsPlayer, (int) (interpolatedX * blockSize), (int) (interpolatedY * blockSize), (int) interpolatedAngle);
    }

    private void playerUpdateGraphics(Maze maze, MazePosition newPosition, Graphics2D g2d) {
        if (maze.isTarget(newPosition)) {
            drawGraphicsAtPosition(g2d, new SmileyFace(), (int) ((newPosition.getPositionX() + 0.5) * blockSize), (int) ((newPosition.getPositionY() + 0.5) * blockSize),  newPosition.getOrientationDegrees());
        }
        if (maze.isBonus(newPosition)) {
            maze.removeElementsAt(newPosition.getPositionX(), newPosition.getPositionY());
            mazeImage = null;
        }
        if (maze.isDeadly(newPosition)) {
            drawGraphicsAtPosition(g2d, new SadFace(), (int) ((newPosition.getPositionX() + 0.5) * blockSize), (int) ((newPosition.getPositionY() + 0.5) * blockSize), newPosition.getOrientationDegrees());
            throw new SteppedOnDeadlyElementException("Oooops");
        }
    }

    private void drawPlayer(Drawable graphicsPlayer, MazePosition position, Graphics2D g2d) {
        if (graphicsPlayer == null || position == null || maze == null) return;
        drawGraphicsAtPosition(g2d, graphicsPlayer, (int) ((position.getPositionX() + 0.5) * blockSize), (int) ((position.getPositionY() + 0.5) * blockSize),  position.getOrientationDegrees());
        playerUpdateGraphics(maze, position, g2d);
    }

    private void initMazeImage(Maze maze) {
        blockSize = calculateBlockSize(maze);
        if (mazeImage == null || mazeImage.getWidth() != frameWidth || mazeImage.getHeight() != frameHeight) {
            mazeImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mazeImage.createGraphics();
            drawMaze(g2d, maze);
        }
    }

    private void drawMaze(Graphics2D g2d, Maze maze) {
        drawMazeTiles(g2d, maze);
        drawItemsOnMaze(g2d, maze);
    }

    private void drawMazeTiles(Graphics2D g2d, Maze maze) {
        for (int x = 0; x < maze.getSizeX(); x++) {
            for (int y = 0; y < maze.getSizeY(); y++) {
                drawItemAt(g2d, x, y, new Tile());
            }
        }
    }

    private void drawItemsOnMaze(Graphics2D g2d, Maze maze) {
        for (MazeElement item : maze.getMazeElements()) {
            drawItemAt(g2d, item.getPosition().getPositionX(), item.getPosition().getPositionY(), item.getItem());
        }
    }

    private void drawItemAt(Graphics2D g2d, int x, int y, Drawable itemToDraw) {
        double factor = blockSize / (double) itemToDraw.getFACTOR_BASE();
        itemToDraw.draw(g2d, new GraphicsPosition((int) ((x + 0.5) * blockSize), (int) ((y + 0.5) * blockSize), 0, factor));
    }

    private double calculateBlockSize(Maze maze) {
        var size = Math.min(frameWidth / (double) maze.getSizeX(), (frameHeight - 30) / (double) maze.getSizeY()) * 0.9;
        return Math.min(100,size); // limits to max 100;
    }

    private void drawGraphicsAtPosition(Graphics2D g2d, Drawable graphicsPlayer, int x, int y, int angle) {
        double factor = blockSize / (double) graphicsPlayer.getFACTOR_BASE();
        graphicsPlayer.draw(g2d, new GraphicsPosition(x, y, angle, factor));
    }

    private void drawCachedMaze(Graphics2D g2d) {
        g2d.drawImage(mazeImage, 0, 0, null);
        drawPlayer(graphicsPlayer, playerPosition, g2d);
    }

    public void keyPressed(KeyEvent e) {
        if (playerPosition == null || maze == null || graphicsPlayer == null || graphicsPlayer instanceof SadFace ) return;

        MazePosition newPosition = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(maze.isAccessible(playerPosition.getPositionForCurrentOrientationForStep(1))) {
                    playerPosition.MakeStepInCurrentOrientation(1);
                    System.out.println("player.moveForward();");
                    if(maze.isDeadly(playerPosition)){
                        graphicsPlayer = new SadFace();
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                playerPosition.turnLeft();
                System.out.println("player.turnLeft();");
                break;
            case KeyEvent.VK_RIGHT:
                playerPosition.turnRight();
                System.out.println("player.turnRight();");
                break;
        }
        updateMazeImage();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void addKeyListeners(JFrame frame) {
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
}
