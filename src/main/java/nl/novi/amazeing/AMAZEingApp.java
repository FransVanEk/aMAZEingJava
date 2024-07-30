package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.graphics.Cross;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;

import java.awt.*;

public class AMAZEingApp {

    public static void main(String[] args) {
        var challenge = ChallengeFactory.constructRandomChallenge(16);
        var player = challenge.player();
        var test = new BreadthFirstSearchNavigator().findPathToTarget(challenge.maze(),0,0);
        player.followInstructions(test);
//        player.setSpeed(10);
//        player.showMaze();
//        player.moveForward();
//        player.moveForward();
//        player.moveForward();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
//        player.moveForward();
//        player.moveForward();
//        player.turnLeft();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
    }
}
