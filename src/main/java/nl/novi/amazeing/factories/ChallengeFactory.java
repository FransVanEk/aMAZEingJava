package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.OpenDoor;
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
        var player = new Player(new OpenDoor(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight).setSpeed(10);
        maze.addMazeElements(MazeElementsFactory.openDoorAsTarget(1, 2));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(1, 3));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(4, 1));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(2, 2));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(3, 3));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(1, 1));
        maze.addMazeElements(MazeElementsFactory.plusAsBonus(1, 0));
        maze.addMazeElements(MazeElementsFactory.plusAsBonus(2, 1));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(0, 1));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge2() {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(6, 2);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElements(MazeElementsFactory.openDoorAsTarget(5, 0));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber(4) + 1, 0));
        return new Challenge(maze, player);
    }

    public static Challenge constructChallenge3() {
        var graphicsRunner = new GraphicsRunner();
        var mazeX = RandomHelpers.getRandomNumber(20) + 4;
        var maze = new Maze(mazeX, 2);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight);
        maze.addMazeElements(MazeElementsFactory.openDoorAsTarget(mazeX - 1, 0));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber((mazeX / 2)) + 1, 0));
        maze.addMazeElements(MazeElementsFactory.noEntrySign(RandomHelpers.getRandomNumber((mazeX / 2)) + (mazeX / 2) + 1, 1));
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
                maze.addMazeElements(MazeElementsFactory.openDoorAsTarget(x, y));
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
            maze.addMazeElements(MazeElementsFactory.noEntrySign(x, y));
        }
    }

    public static Challenge Les_1_Opdracht_1(int horizontaalAantal, int verticaalAantal) {
        var graphicsRunner = new GraphicsRunner();
        var maze = new Maze(horizontaalAantal, verticaalAantal);
        var player = new Player(new Triangle(), maze, graphicsRunner);
        player.setPosition(0, 0, Orientation.FacingRight).setSpeed(10);
        return new Challenge(maze, player);
    }
}
