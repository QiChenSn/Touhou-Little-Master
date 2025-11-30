package com.github.qichensn.ai.task;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.register.TaskDataRegister;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.tileentity.TileEntityMaidBeacon;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MtnhTask extends Behavior<EntityMaid> {
    public MtnhTask() {
        super(ImmutableMap.of());
    }

    @Override
    protected void start(ServerLevel level, EntityMaid entity, long gameTime) {
        super.start(level, entity, gameTime);
    }

    @Override
    protected boolean canStillUse(ServerLevel level, EntityMaid entity, long gameTime) {
        return true;
    }

    @Override
    protected void tick(@NotNull ServerLevel level, EntityMaid maid, long gameTime) {
        BlockPos pos = maid.getOrCreateData(TaskDataRegister.BIND_BE_POS, BlockPos.ZERO);
        // 检查女仆位置
        if(!maid.isAlive() || maid.distanceToSqr(pos.getX(),pos.getY(),pos.getZ()) > 1){
            return;
        }
        // 检查庭灯
        BlockPos beaconPos = getMaidBeacon(level, pos);
        if (beaconPos == null){
            return;
        }
        TileEntityMaidBeacon beacon = (TileEntityMaidBeacon)level.getBlockEntity(beaconPos);
        if(beacon == null){
            return;
        }
        float power = beacon.getStoragePower();
        // TODO: 可配置消耗
        if(power < 1f){
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if(blockEntity ==  null){
            return;
        }
        // 加速Be
        accelerateBlockEntityTick(level,pos,1);
    }

    private @Nullable BlockPos getMaidBeacon(ServerLevel level, BlockPos pos){
        // 检查坐标的六个面
        List<BlockPos> posList = List.of(
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west(),
                pos.above(),
                pos.below()
        );
        for (BlockPos blockPos : posList) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof TileEntityMaidBeacon){
                return blockPos;
            }
        }
        return null;
    }

    public static void accelerateBlockEntityTick(ServerLevel level, BlockPos pos, int times) {
        BlockState state = level.getBlockState(pos);
        BlockEntity be = level.getBlockEntity(pos);

        // 基本检查：确保存在有效的BlockEntity
        if (be == null || be.isRemoved() || !(state.getBlock() instanceof EntityBlock entityBlock)) {
            return;
        }

        // 获取方块实体的ticker
        BlockEntityTicker<BlockEntity> ticker = (BlockEntityTicker<BlockEntity>)
                entityBlock.getTicker(level, state, be.getType());

        // 检查ticker是否存在
        if (ticker == null) {
            return;
        }

        // 执行多次tick
        for (int i = 0; i < times; i++) {
            if (be.isRemoved()) break; // 防止方块实体已被移除
            ticker.tick(level, pos, state, be);
            TouhouLittleMaster.LOGGER.info("加速了{}",pos);
        }

        // 添加粒子效果显示加速发生
        level.sendParticles(
                ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                Math.min(times * 2, 20),
                0.3,
                0.3,
                0.3,
                0.01
        );
    }
}
