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
        add("tooltip.id_card.be","方块实体坐标: %s");
        add("tooltip.id_card.maid","女仆UUID: %s");
        add("message.id_card.bind_be","绑定至%s");
        add("message.id_card.bind_maid","绑定至%s");
        add("message.id_card.task_not_mtnh","请先将女仆切换为MTHN模式");
        add("message.id_card.be_null","未绑定工作方块");
        add("message.id_card.be_not_empty","工作方块上方可用空间不足");
    }
}
