package com.github.qichensn.register;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.data.component.IDCardComponent;
import com.github.qichensn.item.IDCardItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TouhouLittleMaster.MOD_ID);

    public static DeferredItem<Item> ID_CARD = ITEMS.register("id_card",
            () -> new IDCardItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .component(DataComponentRegister.ID_CARD_COMPONENT.value(),new IDCardComponent())
            ));
}
