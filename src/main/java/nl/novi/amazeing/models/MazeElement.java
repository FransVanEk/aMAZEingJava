package nl.novi.amazeing.models;

import nl.novi.amazeing.graphics.Drawable;
import nl.novi.amazeing.models.position.EnumWrapper;
import nl.novi.amazeing.models.position.MazePosition;
import nl.novi.amazeing.models.position.MazeTileDiscoveryEffects;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class MazeElement {
    MazePosition position;
    Drawable Item;
    private EnumWrapper<MazeTileDiscoveryEffects> effects = new EnumWrapper<>(MazeTileDiscoveryEffects.class);

    public MazeElement(MazePosition position, Drawable item, MazeTileDiscoveryEffects... effects) {
        this.position = position;
        Item = item;
        Arrays.stream(effects).forEach(this.effects::addEffect);
    }

    public MazePosition getPosition() {
        return position;
    }

    public Drawable getItem() {
        return Item;
    }

    public Set<MazeTileDiscoveryEffects> getEffects() {
        return effects.getAllEffects();
    }
}
