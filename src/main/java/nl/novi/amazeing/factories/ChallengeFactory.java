package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.Triangle;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.Player;
import nl.novi.amazeing.models.position.Orientation;

public class ChallengeFactory {
    public static Challenge constructChallenge1() {
        var graphicsRunnner = new GraphicsRunner();
        var maze = new Maze(5,5);
        var player = new Player(new Triangle(), maze, graphicsRunnner);
        player.setPosition(0,0, Orientation.FacingRight);
        maze.addMazeElements(MazeElementsFactory.OpenDoorAsTarget(1,2));
        maze.addMazeElements(MazeElementsFactory.CrossAsNoEntry(1,1));
        maze.addMazeElements(MazeElementsFactory.CrossAsNoEntry(1,3));
        maze.addMazeElements(MazeElementsFactory.CrossAsNoEntry(4,1));
        maze.addMazeElements(MazeElementsFactory.CrossAsNoEntry(2,2));
        maze.addMazeElements(MazeElementsFactory.CrossAsNoEntry(3,3));
        maze.addMazeElements(MazeElementsFactory.PlusAsBonus(1,0));
        maze.addMazeElements(MazeElementsFactory.PlusAsBonus(2,1));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(0,1));
        return new Challenge(maze, player);
    }
}
