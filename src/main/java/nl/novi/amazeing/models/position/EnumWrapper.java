package nl.novi.amazeing.models.position;

import java.util.EnumSet;
import java.util.Set;

public class EnumWrapper<T extends Enum<T>> {
    private EnumSet<T> effects;

    public EnumWrapper(Class<T> enumType) {
        this.effects = EnumSet.noneOf(enumType);
    }

    public void addEffect(T effect) {
        effects.add(effect);
    }

    public void revokeEffect(T effect) {
        effects.remove(effect);
    }

    public boolean hasEffect(T effect) {
        return effects.contains(effect);
    }

    public Set<T> getAllEffects() {
        return effects;
    }
}
