package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 5;
        int verticaalAantal = 8;

        // Maak de uitdaging aan met variabelen
        var challenge = ChallengeFactory.constructChallenge1(horizontaalAantal, verticaalAantal);
        var player = challenge.player();
        player.showMaze();
    }
}