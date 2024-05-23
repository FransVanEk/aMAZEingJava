package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.factories.MazeElementsFactory;
import nl.novi.amazeing.navigators.BreadthFirstSearchNavigator;



public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 5;
        int verticaalAantal = 5;
        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.constructRandomChallenge(6);
        challenge.maze().addMazeElements(MazeElementsFactory.CrossAsDeadly(3,0));
        challenge.maze().addMazeElements(MazeElementsFactory.CrossAsDeadly(5,1));
        var player = challenge.player();
        player.setSpeed(10);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();
//        while(player.isSaveToMoveForward()) {
//            player.moveForward();
//        }
//        if(player.isSaveToMoveRight()){
//            player.turnRight();
//            player.moveForward();
//        }
//        if(player.isSaveToMoveLeft()){
//            player.turnLeft();
//            player.moveForward();
//        }

//        Instruction[] instructies = {
//                Instruction.TURNRIGHT,
//                Instruction.FORWARD,
//                Instruction.TURNRIGHT,
//                Instruction.FORWARD,
//                Instruction.TURNRIGHT,
//                Instruction.FORWARD,
//                Instruction.TURNRIGHT,
//                Instruction.FORWARD,
//                Instruction.FORWARD,
//                Instruction.TURNLEFT,
//                Instruction.FORWARD,
//                Instruction.FORWARD,
//                Instruction.FORWARD,
//                Instruction.TURNLEFT,
//                Instruction.FORWARD,
//                Instruction.FORWARD
//        };

        var instructies = new BreadthFirstSearchNavigator().findPathToTarget(challenge.maze(),0,0);
        player.followInstructions(instructies);
    }
}
