package com.github.qichensn.task;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.ai.task.MtnhTask;
import com.github.qichensn.register.ItemRegister;
import com.github.tartaricacid.touhoulittlemaid.api.task.IMaidTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MTNHTask implements IMaidTask {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(TouhouLittleMaster.MOD_ID,
            "mtnh");
    @Override
    public @NotNull ResourceLocation getUid() {
        return UID;
    }

    @Override
    public @NotNull ItemStack getIcon() {
        return ItemRegister.ID_CARD.toStack();
    }

    @Override
    public @Nullable SoundEvent getAmbientSound(EntityMaid maid) {
        return null;
    }

    @Override
    public List<Pair<Integer, BehaviorControl<? super EntityMaid>>> createBrainTasks(EntityMaid maid) {
        List<Pair<Integer, BehaviorControl<? super EntityMaid>>> tasks = new ArrayList<>();
        tasks.add(Pair.of(10,new MtnhTask()));
        return tasks;
    }

    @Override
    public boolean enableLookAndRandomWalk(EntityMaid maid) {
        return false;
    }

    @Override
    public boolean enablePanic(EntityMaid maid) {
        return false;
    }
}
