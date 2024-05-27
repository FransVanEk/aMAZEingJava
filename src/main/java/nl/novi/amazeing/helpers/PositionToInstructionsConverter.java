package nl.novi.amazeing.helpers;

import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.Orientation;
import nl.novi.amazeing.navigators.Instruction;

import java.util.ArrayList;
import java.util.List;

public abstract class PositionToInstructionsConverter {
    public static List<Instruction> convertToInstructions(List<MazePosition> path) {
        List<Instruction> instructions = new ArrayList<>();
        if (path.size() < 2) return instructions;

        Orientation currentOrientation = Orientation.FacingRight;
        for (int i = 0; i < path.size() - 1; i++) {
            MazePosition current = path.get(i);
            MazePosition next = path.get(i + 1);
            Orientation requiredOrientation = determineRequiredOrientation(current, next);
            instructions.addAll(generateTurnInstructions(currentOrientation, requiredOrientation));
            instructions.add(Instruction.FORWARD);
            currentOrientation = requiredOrientation;
        }
        return instructions;
    }

    private static List<Instruction> generateTurnInstructions(Orientation current, Orientation required) {
        List<Instruction> instructions = new ArrayList<>();
        while (current != required) {
            if (isClockwiseTurn(current, required)) {
                instructions.add(Instruction.TURNRIGHT);
                current = current.turnRight();
            } else {
                instructions.add(Instruction.TURNLEFT);
                current = current.turnLeft();
            }
        }
        return instructions;
    }

    private static boolean isClockwiseTurn(Orientation current, Orientation required) {
        return (current == Orientation.FacingRight && required == Orientation.FacingDown) ||
                (current == Orientation.FacingDown && required == Orientation.FacingLeft) ||
                (current == Orientation.FacingLeft && required == Orientation.FacingUp) ||
                (current == Orientation.FacingUp && required == Orientation.FacingRight);
    }


    private static Orientation determineRequiredOrientation(MazePosition current, MazePosition next) {
        if (current.getPositionX() == next.getPositionX()) {
            return next.getPositionY() > current.getPositionY() ? Orientation.FacingDown : Orientation.FacingUp;
        }
        return next.getPositionX() > current.getPositionX() ? Orientation.FacingRight : Orientation.FacingLeft;
    }

}
