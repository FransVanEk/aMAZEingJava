package nl.novi.amazeing;

import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructChallenge2();
        var player = challenge.player();
        player.setSpeed(100);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        // Gebruik een while-loop om de speler naar het doelpunt te bewegen
        while (!player.isOnTarget()) {
            while (player.canMoveForward() && !player.isOnTarget()) {
                player.moveForward();
            }
            player.turnRight();
        }
    }
}