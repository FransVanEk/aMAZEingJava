package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.navigators.SimpleNavigator;


public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 9;
        int verticaalAantal = horizontaalAantal / 3;

        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.Les_1_Opdracht_1(horizontaalAantal, verticaalAantal);
        var player = challenge.player();
        player.setSpeed(10);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();
//
        var instructies = new SimpleNavigator().findPathToTarget(challenge.maze(),0,0);
        player.followInstructions(instructies);
    }
}
