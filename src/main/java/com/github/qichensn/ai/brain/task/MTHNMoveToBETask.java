package com.github.qichensn.ai.brain.task;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.item.IDCardItem;
import com.github.qichensn.register.TaskDataRegister;
import com.github.qichensn.task.MTNHTask;
import com.github.tartaricacid.touhoulittlemaid.entity.ai.brain.task.MaidMoveToBlockTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import org.apache.commons.lang3.ObjectUtils;

public class MTHNMoveToBETask extends MaidMoveToBlockTask {
    public MTHNMoveToBETask(float movementSpeed, int verticalSearchRange) {
        super(movementSpeed, verticalSearchRange);
    }

    @Override
    protected boolean shouldMoveTo(ServerLevel worldIn, EntityMaid entityIn, BlockPos pos) {
        // 女仆处于MTNH模式
        if(!(entityIn.getTask() instanceof MTNHTask)) return false;
        BlockPos targetPos = entityIn.getOrCreateData(TaskDataRegister.BIND_BE_POS, BlockPos.ZERO);
        if(targetPos.equals(BlockPos.ZERO)) return false;
        if(targetPos.distSqr(pos)<=2.0) return false;
        if(worldIn.getBlockEntity(targetPos)==null) return false;
        TouhouLittleMaster.LOGGER.info("正在寻路中，当前位置{}，目标位置{}，距离{}", pos, targetPos, pos.distSqr(targetPos));
        return true;
    }

    @Override
    protected void tick(ServerLevel level, EntityMaid owner, long gameTime) {
        super.tick(level, owner, gameTime);
        if(!(owner.getTask() instanceof MTNHTask)) return;
        CombinedInvWrapper inv = owner.getAllInv();
        for(int i = 0; i < inv.getSlots(); i++){
            ItemStack stack = inv.getStackInSlot(i);
            if(stack.isEmpty()){
                continue;
            }
            if(stack.getItem() instanceof IDCardItem){
                owner.setData(TaskDataRegister.BIND_BE_POS, ObjectUtils.defaultIfNull(IDCardItem.getBePos(stack), BlockPos.ZERO));
                break;
            }
        }
    }
}
