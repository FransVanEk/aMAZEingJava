package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.graphics.Cross;
import nl.novi.amazeing.graphics.SadFace;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.models.position.PositionMetaData;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;

public class AMAZEingApp {

    public static void main(String[] args) {

        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructSpiralMaze(9);
        var player = challenge.player();
        player.changeAppearance(new SadFace());
        player.setSpeed(100);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        var instructions = new BreadthFirstSearchNavigator().findPathToTarget(challenge.maze(),0,0);
        player.followInstructions(instructions);

//        while(!player.isOnTarget()) {
//            while (player.isSaveToMoveForward() && !player.isOnTarget()) {
//                player.moveForward();
//            }
//            player.turnRight();
//        }
    }
}
