package com.github.qichensn.datagen;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.register.ItemRegister;
import com.github.qichensn.task.MTNHTask;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.qichensn.TouhouLittleMaster.MOD_ID;

public class ChineseLangProvider extends LanguageProvider {
    public ChineseLangProvider(PackOutput output) {
        super(output, MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(ItemRegister.ID_CARD.get(),"工牌");
        add(String.format("task.%s.mtnh", MOD_ID), "MTNH");
        add(String.format("task.%s.mtnh.desc",MOD_ID),"使用工牌绑定女仆和机器");
    }
} 