package com.github.qichensn.register;

import net.neoforged.bus.api.IEventBus;

public class ModRegister {
    public static void register(IEventBus eventBus){
        ItemRegister.ITEMS.register(eventBus);
        DataComponentRegister.DATA_COMPONENTS.register(eventBus);
    }
}
