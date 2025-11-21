package com.github.qichensn.register;

import com.github.qichensn.TouhouLittleMaster;
import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.github.tartaricacid.touhoulittlemaid.api.LittleMaidExtension;
import com.github.tartaricacid.touhoulittlemaid.api.entity.data.TaskDataKey;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;

@LittleMaidExtension
public class TaskDataRegister implements ILittleMaid {
    // MTNH绑定的BE
    public static TaskDataKey<String> BIND_BE_UUID;
    @Override
    public void registerTaskData(com.github.tartaricacid.touhoulittlemaid.entity.data.TaskDataRegister register) {
        BIND_BE_UUID = register.register(
                ResourceLocation.fromNamespaceAndPath(TouhouLittleMaster.MOD_ID, "bind_be_uuid"),
                Codec.STRING.fieldOf("value").codec()
        );
    }
}