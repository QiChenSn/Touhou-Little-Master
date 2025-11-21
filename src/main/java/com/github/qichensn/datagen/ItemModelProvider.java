package com.github.qichensn.datagen;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.register.ItemRegister;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper helper){
        super(output, TouhouLittleMaster.MOD_ID,helper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ItemRegister.ID_CARD);
    }
    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TouhouLittleMaster.MOD_ID,"item/" + item.getId().getPath()));
    }
}
