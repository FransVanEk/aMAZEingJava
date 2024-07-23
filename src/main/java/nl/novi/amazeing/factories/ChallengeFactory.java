package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.Triangle;
import nl.novi.amazeing.helpers.RandomHelpers;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.Player;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;

public class ChallengeFactory {
    public static Challenge constructChallenge1() {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(6, 6);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight).setSpeed(10);
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(1, 2));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(1, 3));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(4, 1));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(2, 2));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(3, 3));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(1, 1));
        maze.addMazeElement(MazeElementsFactory.plusAsBonus(1, 0));
        maze.addMazeElement(MazeElementsFactory.plusAsBonus(2, 1));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(0, 1));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge2() {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(9, 9);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(4, 4));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(8, 8));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(0, 7));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(1, 1));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(7, 2));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(6, 6));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(2, 5));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(3, 3));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge2_1() {
       var challenge = constructChallenge2();
       challenge.maze().removeElementsAt(3,3);
       challenge.maze().addMazeElement(MazeElementsFactory.crossAsDeadly(3,3));
        return challenge;
    }

    public static Challenge constructSimpleNavigatorFlawed() {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(18, 18);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(0, 1));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge4() {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(6, 2);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(5, 0));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber(4) + 1, 0));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge3() {
        var graphicsRunner = new GraphicsRunner();
        var mazeX = RandomHelpers.getRandomNumber(20) + 4;
        var maze = new Maze(mazeX, 2);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(mazeX - 1, 0));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber((mazeX / 2)) + 1, 0));
        maze.addMazeElement(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber((mazeX / 2)) + (mazeX / 2) + 1, 1));
        return new Challenge(maze, player);
    }

    public static Challenge constructRandomChallenge(int size) {

        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(size, size);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        createRandomNoEntryItems((int) ((size * size) * 0.3), maze, player.getPosition());
        placeTarget(maze, player.getPosition());

        return new Challenge(maze, player);
    }

    private static void placeTarget(Maze maze, MazePosition playersPosition) {
        boolean success = false;
        do {
            var x = RandomHelpers.getRandomNumber(maze.getSizeX());
            var y = RandomHelpers.getRandomNumber(maze.getSizeY());
            if (maze.isEmptySpot(x, y) && !playersPosition.isPosition(x, y)) {
                maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(x, y));
                success = true;
            }
        } while (!success);
    }

    private static void createRandomNoEntryItems(int numberOfItems, Maze maze, MazePosition positionPlayer) {
        for (int i = 0; i < numberOfItems; i++) {
            var x = RandomHelpers.getRandomNumber(maze.getSizeX());
            var y = RandomHelpers.getRandomNumber(maze.getSizeY());
            if (x == 0 && y == 0 || positionPlayer.isPosition(x, y)) {
                continue;
            }
            maze.addMazeElement(MazeElementsFactory.noEntrySign(x, y));
        }
    }

    public static Challenge les1Opdracht1(int horizontaalAantal, int verticaalAantal) {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(horizontaalAantal, verticaalAantal);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight).setSpeed(10);
        return new Challenge(maze, player);
    }

    public static Challenge constructSpiralMaze(int size) {
         if(size % 2 == 0) {size +=1;}
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(size, size);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight).setSpeed(10);

        int x = 0, y = 0;
        int dx = 1, dy = 0;
        int layer = 0;

        while (layer < size / 2) {
            for (int i = 0; i < size - 1 - layer * 2; i++) {
                maze.addMazeElement(MazeElementsFactory.noEntrySign(x-1, y+1));
                x += dx;
                y += dy;
            }
            int temp = dx;
            dx = -dy;
            dy = temp;
            if (dx == 0) layer++;
        }

        // Ensure there is a clear path from (0, 0) to the center of the maze
        int centerX = size / 2;
        int centerY = size / 2;

        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(centerX-1, centerY+1));
        maze.addMazeElement(MazeElementsFactory.openDoorAsTarget(centerX, centerY));

        return new Challenge(maze, player);
    }
}
