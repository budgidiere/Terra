package com.dfsek.terra.api.structures.structure.buffer;

import com.dfsek.terra.api.math.vector.Location;
import com.dfsek.terra.api.structures.structure.buffer.items.BufferedItem;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;

import java.util.Map;

/**
 * Buffer implementation that directly pastes to the world.
 */
public class DirectBuffer implements Buffer {
    private final Location origin;
    private final Map<Location, String> marks = new Object2ObjectLinkedOpenHashMap<>();

    public DirectBuffer(Location origin) {
        this.origin = origin;
    }

    @Override
    public Buffer addItem(BufferedItem item, Location location) {
        item.paste(origin.clone().add(location));
        return this;
    }

    @Override
    public Location getOrigin() {
        return origin;
    }

    @Override
    public String getMark(Location location) {
        return marks.get(location);
    }

    @Override
    public Buffer setMark(String mark, Location location) {
        marks.put(location, mark);
        return this;
    }
}
