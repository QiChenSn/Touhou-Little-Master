package com.github.qichensn.datagen;

import net.minecraft.core.HolderLookup;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGenerator {
    public DataGenerator(IEventBus eventBus){
        eventBus.addListener(DataGenerator::onGatherData);
    }

    public static void onGatherData(GatherDataEvent event) {
        var gen=event.getGenerator();
        var output=gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        ExistingFileHelper helper = event.getExistingFileHelper();
        gen.addProvider(event.includeClient(),new ItemModelProvider(output,helper));
        gen.addProvider(event.includeClient(),new LangProvider(output,"en_us"));
        gen.addProvider(event.includeClient(),new ChineseLangProvider(output));
        gen.addProvider(event.includeClient(),new RecipeProvider(output,lookupProvider));
    }
}
