package nl.novi.amazeing.factories;

import nl.novi.amazeing.graphics.Cross;
import nl.novi.amazeing.graphics.OpenDoor;
import nl.novi.amazeing.graphics.PlusSign;
import nl.novi.amazeing.models.MazeElement;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.MazeTileDiscoveryEffects;
import nl.novi.amazeing.models.position.Orientation;

public class MazeElementsFactory {

    public static MazeElement OpenDoorAsTarget(int x, int y)
    {
        return new MazeElement(new MazePosition(x,y, Orientation.FacingRight),  new OpenDoor(), MazeTileDiscoveryEffects.ISTARGET,MazeTileDiscoveryEffects.ISACCESSABLE);
    }

    public static MazeElement PlusAsBonus(int x, int y)
    {
       return  new MazeElement(new MazePosition(x,y, Orientation.FacingRight),  new PlusSign(), MazeTileDiscoveryEffects.ISBONUS);
    }

    public static MazeElement CrossAsDeadly(int x, int y)
    {
        return  new MazeElement(new MazePosition(x,y, Orientation.FacingRight),  new Cross(), MazeTileDiscoveryEffects.ISDEADLY);
    }

}
