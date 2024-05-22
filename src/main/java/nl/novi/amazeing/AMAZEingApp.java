package nl.novi.amazeing;


import nl.novi.amazeing.factories.ChallengeFactory;
import nl.novi.amazeing.factories.MazeElementsFactory;
import nl.novi.amazeing.navigators.Instruction;


public class AMAZEingApp {

    public static void main(String[] args) {
        int horizontaalAantal = 6;
        int verticaalAantal = 3;
        String spelerNaam = "jouw naam";

        var challenge = ChallengeFactory.Les_1_Opdracht_1(horizontaalAantal, verticaalAantal);
        challenge.maze().addMazeElements(MazeElementsFactory.CrossAsDeadly(3,0));
        challenge.maze().addMazeElements(MazeElementsFactory.CrossAsDeadly(3,1));
        var player = challenge.player();
        player.setSpeed(50);
        // Zet de naam van de speler
        player.setName(spelerNaam);
        player.showMaze();
        while(player.isSaveToMoveForward()) {
            player.moveForward();
        }
        if(player.isSaveToMoveRight()){
            player.turnRight();
            player.moveForward();
        }
        if(player.isSaveToMoveLeft()){
            player.turnLeft();
            player.moveForward();
        }

        Instruction[] instructies = {
                Instruction.TURNRIGHT,
                Instruction.FORWARD,
                Instruction.TURNRIGHT,
                Instruction.FORWARD,
                Instruction.TURNRIGHT,
                Instruction.FORWARD
        };
        player.followInstructions(instructies);
    }
}
