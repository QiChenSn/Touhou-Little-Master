package com.github.qichensn.register;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.data.component.IDCardComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TouhouLittleMaster.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<IDCardComponent>> ID_CARD_COMPONENT =
            DATA_COMPONENTS.registerComponentType(
            "id_card",
            builder -> builder.networkSynchronized(IDCardComponent.STREAM_CODEC)
    );
}
