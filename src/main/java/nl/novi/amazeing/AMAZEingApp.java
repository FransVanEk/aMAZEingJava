package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;
import nl.novi.amazeing.navigators.Instruction;


public class AMAZEingApp {

    public static void main(String[] args) {
        var challenge = ChallengeFactory.constructRandomChallenge();
        var instructions = new BreadthFirstSearchNavigator(challenge.getMaze()).findPathToTarget(0,0);
        var player = challenge.getPlayer();
        player.showMaze();

        for (Instruction action : instructions) {
            switch (action) {
                case FORWARD -> { player.moveForward();
                }
                case BACKWARD -> {
                    player.moveBackward();
                }
                case TURNLEFT -> {
                    player.turnLeft();
                }
                case TURNRIGHT -> {
                    player.turnRight();
                }
            }
        }



//        player.turnRight();
//        if (player.canMoveForward()) {
//            player.moveForward();
//        } else {
//            player.turnLeft();
//        }
//
//        player.moveForward();
//        player.moveForward();
//        player.turnRight();
//        player.moveForward();
//        if (player.getEffectsForMovingForward().contains(PositionMetaData.ISTARGET)){
//            player.moveForward();
//        }

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
