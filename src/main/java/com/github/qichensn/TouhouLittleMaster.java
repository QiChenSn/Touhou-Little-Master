package com.github.qichensn;

import com.github.qichensn.register.ModRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TouhouLittleMaster.MOD_ID)
public class TouhouLittleMaster {
    public static final String MOD_ID = "touhou_little_master";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TouhouLittleMaster(IEventBus modEventBus, ModContainer modContainer) {
        ModRegister.register(modEventBus);
    }
}
