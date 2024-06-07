package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.graphics.GraphicsRunner;
import nl.novi.amazeing.graphics.SadFace;
import nl.novi.amazeing.models.Player;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;
import nl.novi.amazeing.navigators.RandomizedSearchNavigator;

public class AMAZEingApp {

    public static void main(String[] args) {
        var challenge = ChallengeFactory.constructChallenge1();
        var player = challenge.player();
        player.setSpeed(10);
        player.showMaze();
        player.findExit();
    }
}
