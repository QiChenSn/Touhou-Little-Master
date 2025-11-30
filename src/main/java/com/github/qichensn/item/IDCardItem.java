package com.github.qichensn.item;

import com.github.qichensn.TouhouLittleMaster;
import com.github.qichensn.data.component.IDCardComponent;
import com.github.qichensn.register.TaskDataRegister;
import com.github.qichensn.task.MTNHTask;
import com.github.tartaricacid.touhoulittlemaid.api.event.InteractMaidEvent;
import com.github.tartaricacid.touhoulittlemaid.api.task.IMaidTask;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.qichensn.register.DataComponentRegister.ID_CARD_COMPONENT;

@EventBusSubscriber(modid = TouhouLittleMaster.MOD_ID)
public class IDCardItem extends Item {
    public IDCardItem(Properties properties) {
        super(properties);
    }

    private static void setMaidUUID(ItemStack stack, String uuid){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            stack.set(ID_CARD_COMPONENT, new IDCardComponent(uuid, component.bePos()));
        }
    }
    private static void setBePos(ItemStack stack, BlockPos pos){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            stack.set(ID_CARD_COMPONENT, new IDCardComponent(component.maidUUID(), pos));
        }
    }
    private static String getMaidUUID(ItemStack stack){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            return component.maidUUID();
        }
        return null;
    }
    private static BlockPos getBePos(ItemStack stack){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            return component.bePos();
        }
        return null;
    }
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (player == null || level.isClientSide()) {
            return InteractionResult.FAIL;
        }
        BlockPos blockPos = context.getClickedPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity != null) {
            setBePos(context.getItemInHand(), blockPos);
            String pos = getBePos(context.getItemInHand()) == null ? "null" : getBePos(context.getItemInHand()).toShortString();
            player.displayClientMessage(Component.translatable("message.id_card.bind_be",
                    pos), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String pos = getBePos(stack) == null ? "null" : getBePos(stack).toShortString();
        String uuid = getMaidUUID(stack) == null ? "null" : getMaidUUID(stack);
        tooltipComponents.add(Component.translatable("tooltip.id_card.be",pos));
        tooltipComponents.add(Component.translatable("tooltip.id_card.maid",uuid));
    }

    @SubscribeEvent
    public static void maidInteract(InteractMaidEvent event){
        Level level = event.getWorld();
        if(level.isClientSide()) return;
        EntityMaid maid = event.getMaid();
        ItemStack stack = event.getStack();
        Player player = event.getPlayer();
        if(!(stack.getItem() instanceof IDCardItem)) return;
        bindAndSendMaidToWork((ServerLevel) level, stack, maid, (ServerPlayer) player);
        event.setCanceled( true);
    }

    public static void bindAndSendMaidToWork(ServerLevel level, ItemStack stack, EntityMaid maid, ServerPlayer player){
        // 检查工作模式
        IMaidTask task = maid.getTask();
        if(!(task instanceof MTNHTask)){
            player.displayClientMessage(Component.translatable("message.id_card.task_not_mtnh"), true);
            return;
        }
        // 检查工作方块上方两格是否为空
        BlockPos pos = getBePos(stack);
        if(pos == null){
            player.displayClientMessage(Component.translatable("message.id_card.be_null"), true);
            return;
        }
        if(!level.getBlockState(pos.above()).isAir() || !level.getBlockState(pos.above(2)).isAir()){
            player.displayClientMessage(Component.translatable("message.id_card.be_not_empty"), true);
            return;
        }
        setMaidUUID(stack,maid.getStringUUID());
        player.displayClientMessage(Component.translatable("message.id_card.bind_maid",
                maid.getStringUUID()), true);
        // 传送女仆
        BlockPos above = pos.above(1);
        // TODO: 不知道为什么，传送位置太远的时候，女仆会自动传送回去
        maid.teleportTo(above.getCenter().x, above.getCenter().y, above.getCenter().z);
        maid.setData(TaskDataRegister.BIND_BE_POS,pos);
    }
}
