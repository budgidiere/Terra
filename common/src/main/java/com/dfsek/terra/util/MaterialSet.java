package com.dfsek.terra.util;

import com.dfsek.terra.api.platform.block.BlockData;
import com.dfsek.terra.api.platform.block.MaterialData;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.Arrays;

public class MaterialSet extends ObjectOpenHashSet<MaterialData> {
    private static final long serialVersionUID = 3056512763631017301L;

    public static MaterialSet singleton(MaterialData material) {
        MaterialSet set = new MaterialSet();
        set.add(material);
        return set;
    }

    public static MaterialSet get(MaterialData... materials) {
        MaterialSet set = new MaterialSet();
        set.addAll(Arrays.asList(materials));
        return set;
    }

    private void add(BlockData data) {
        add(data.getMaterial());
    }
}
