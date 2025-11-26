package com.github.qichensn.datagen;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.register.ItemRegister;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.github.qichensn.TouhouLittleMaster.MOD_ID;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output, String locale) {
        super(output, TouhouLittleMaster.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ItemRegister.ID_CARD.get(),"工牌");
        add(String.format("task.%s.mtnh", MOD_ID), "MTNH");
        add(String.format("task.%s.mtnh.desc",MOD_ID),"使用工牌绑定女仆和机器");
        add("tooltip.id_card_be","方块实体坐标: %s");
        add("tooltip.id_card_maid","女仆UUID: %s");
    }
}
