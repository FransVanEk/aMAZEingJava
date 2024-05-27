package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {

        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructChallenge2_1();
        var player = challenge.player();
        player.setSpeed(100);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();

        while(!player.isOnTarget()) {
            while (player.isSaveToMoveForward() && !player.isOnTarget()) {
                player.moveForward();
            }
            player.turnRight();
        }
    }
}
