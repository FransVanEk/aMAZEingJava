package nl.novi.amazeing.models;

import nl.novi.amazeing.graphics.Drawable;
import nl.novi.amazeing.models.position.EnumWrapper;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.PositionMetaData;

import java.util.Arrays;
import java.util.Set;

public class MazeElement {
    MazePosition position;
    Drawable Item;
    private EnumWrapper<PositionMetaData> positionMetaData = new EnumWrapper<>(PositionMetaData.class);

    public MazeElement(MazePosition position, Drawable item, PositionMetaData... positionMetaData) {
        this.position = position;
        Item = item;
        Arrays.stream(positionMetaData).forEach(this.positionMetaData::addEnumItem);
    }

    public MazePosition getPosition() {
        return position;
    }

    public Drawable getItem() {
        return Item;
    }

    public Set<PositionMetaData> getPositionMetaData() {
        return positionMetaData.getAllEnumItems();
    }
}
