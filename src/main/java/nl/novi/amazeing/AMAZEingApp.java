package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;

public class AMAZEingApp {

    public static void main(String[] args) {
        var player = ChallengeFactory.constructChallenge1().getPlayer();
        player.showMaze();
        player.moveForward();
        player.moveForward();
        player.turnRight();
        player.moveForward();
        player.moveForward();
//        player.turnLeft();
//        player.turnLeft();
//        player.turnLeft();
//        player.turnLeft();
//        player.moveForward();
//        player.turnRight();
//        player.turnRight();
//        player.turnRight();
//        player.turnRight();
//        player.turnRight();
//        player.turnRight();
//        player.turnRight();
        System.out.println( "Yay you made it");
    }
}
