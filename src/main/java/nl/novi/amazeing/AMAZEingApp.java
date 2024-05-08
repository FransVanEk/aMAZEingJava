package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;
import nl.novi.amazeing.navigators.RandomizedSearchNavigator;


public class AMAZEingApp {

    public static void main(String[] args) {

        var challenge = ChallengeFactory.constructRandomChallenge(10);
        var player = challenge.player();
        player.setSpeed(10);
        var instructions = new RandomizedSearchNavigator(challenge.maze()).findPathToTarget(player.getPosition().getPositionX(), player.getPosition().getPositionY());
        player.showMaze();
        player.followInstructions(instructions);
        System.out.println("Yay you made it");
    }
}
