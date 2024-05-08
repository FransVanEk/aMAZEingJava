package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;
import nl.novi.amazeing.navigators.RandomizedSearchNavigator;


public class AMAZEingApp {

    public static void main(String[] args) {

        var challenge = ChallengeFactory.constructRandomChallenge(20);
        var player = challenge.player();
        player.setSpeed(80);
        var instructions = new BreadthFirstSearchNavigator(challenge.maze()).findPathToTarget(player.getPosition().getPositionX(), player.getPosition().getPositionY());
        player.showMaze();
        player.followInstructions(instructions);
        System.out.println("Yay you made it");
    }
}
