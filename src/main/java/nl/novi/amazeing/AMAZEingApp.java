package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.graphics.Cross;

import java.awt.*;

public class AMAZEingApp {

    public static void main(String[] args) {
        var challenge = ChallengeFactory.constructChallenge2_1();
        var player = challenge.player();
        player.setSpeed(5);
        player.showMaze();
        player.moveForward();
        player.moveForward();
        player.moveForward();
        player.moveForward();
        player.turnRight();
        player.moveForward();
        player.moveForward();
        player.moveForward();
        player.turnLeft();
        player.moveForward();
        player.turnRight();
        player.moveForward();
        player.moveForward();
        player.turnRight();
        player.moveForward();
        player.moveForward();
        player.turnRight();
        player.moveForward();
        player.turnRight();
        player.moveForward();
    }
}
