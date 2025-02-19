package nl.novi.amazeing;

import nl.novi.amazeing.factories.Challenge;
import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;
import nl.novi.amazeing.navigators.RandomizedSearchNavigator;
import nl.novi.amazeing.navigators.SimpleNavigator;

public class AMAZEingApp {

    public static void main(String[] args) {
        // Maak de uitdaging aan met variabelen
        var challenge = ChallengeFactory.constructRandomChallenge(20);
        var player = challenge.player();
        player.setName("Speedy");
        player.showMaze();
        player.setSpeed(100);
        var test = new BreadthFirstSearchNavigator().findPathToTarget(challenge.maze(),0,0);
        player.followInstructions(test);
    }
}