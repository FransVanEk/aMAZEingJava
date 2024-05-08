package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.Cross;
import nl.novi.amazeing.graphics.NoEntrySign;
import nl.novi.amazeing.graphics.OpenDoor;
import nl.novi.amazeing.graphics.PlusSign;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;
import nl.novi.amazeing.models.position.Orientation;

public class MazeElementsFactory {

    public static MazeElement OpenDoorAsTarget(int x, int y) {
        return new MazeElement(new MazePosition(x, y, Orientation.FacingRight), new OpenDoor(), PositionMetaData.IS_TARGET, PositionMetaData.IS_ACCESSIBLE);
    }

    public static MazeElement PlusAsBonus(int x, int y) {
        return new MazeElement(new MazePosition(x, y, Orientation.FacingRight), new PlusSign(), PositionMetaData.IS_BONUS);
    }

    public static MazeElement CrossAsDeadly(int x, int y) {
        return new MazeElement(new MazePosition(x, y, Orientation.FacingRight), new Cross(), PositionMetaData.IS_DEADLY);
    }

    public static MazeElement CrossAsNoEntry(int x, int y) {
        return new MazeElement(new MazePosition(x, y, Orientation.FacingRight), new Cross(), PositionMetaData.NO_ENTRY);
    }

    public static MazeElement NoEntrySign(int x, int y) {
        return new MazeElement(new MazePosition(x, y, Orientation.FacingRight), new NoEntrySign(), PositionMetaData.NO_ENTRY);
    }

}
