package nl.novi.amazeing.models.position;

import java.util.EnumSet;
import java.util.Set;

public class EnumWrapper<T extends Enum<T>> {
    private final EnumSet<T> enumItems;

    public EnumWrapper(Class<T> enumType) {
        this.enumItems = EnumSet.noneOf(enumType);
    }

    public void addEnumItem(T enumItem) {
        enumItems.add(enumItem);
    }

    public void revokeEnumItem(T enumItem) {
        enumItems.remove(enumItem);
    }

    public boolean hasEnumItem(T enumItem) {
        return enumItems.contains(enumItem);
    }

    public Set<T> getAllEnumItems() {
        return enumItems;
    }
}
