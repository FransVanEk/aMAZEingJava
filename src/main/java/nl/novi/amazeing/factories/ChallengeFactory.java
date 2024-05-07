package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.Triangle;
import nl.novi.amazeing.helpers.RandomHelpers;
import nl.novi.amazeing.models.Maze;
import nl.novi.amazeing.models.Player;
import nl.novi.amazeing.models.position.Orientation;

public class ChallengeFactory {
    public static Challenge constructChallenge1() {
        var graphicsRunnner = new GraphicsRunner();
        var maze = new Maze(6,6);
        var player = new Player(new Triangle(), maze, graphicsRunnner);
        player.setPosition(0,0, Orientation.FacingRight);
        maze.addMazeElements(MazeElementsFactory.OpenDoorAsTarget(1,2));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(1,3));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(4,1));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(2,2));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(3,3));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(1,1));
        maze.addMazeElements(MazeElementsFactory.PlusAsBonus(1,0));
        maze.addMazeElements(MazeElementsFactory.PlusAsBonus(2,1));
        maze.addMazeElements(MazeElementsFactory.NoEntrySign(0,1));
        return new Challenge(maze, player);
    }

    public static Challenge constructRandomChallenge() {

        var graphicsRunnner = new GraphicsRunner();
        var maze = new Maze(10,10);
        var player = new Player(new Triangle(), maze, graphicsRunnner);
        player.setPosition(0,0, Orientation.FacingRight);
        createRandomNoEntryItems(25, maze);
        placeTarget(maze);

        return new Challenge(maze, player);
    }

    private static void placeTarget(Maze maze) {
        boolean success = false;
        do{
            var x = RandomHelpers.getRandomNumber(maze.getSizeX());
            var y = RandomHelpers.getRandomNumber(maze.getSizeY());
            if(maze.isEmptySpot(x,y)){
                maze.addMazeElements(MazeElementsFactory.OpenDoorAsTarget(x,y));
                success = true;
            }
        }while(!success);
    }

    private static void createRandomNoEntryItems(int numberOfItems, Maze maze) {
        for(int i =0 ; i< numberOfItems; i++){
            var x = RandomHelpers.getRandomNumber(maze.getSizeX());
            var y = RandomHelpers.getRandomNumber(maze.getSizeY());
            if(x==0 && y == 0){
                continue;
            }
            maze.addMazeElements(MazeElementsFactory.NoEntrySign(x,y));
        }
    }
}
