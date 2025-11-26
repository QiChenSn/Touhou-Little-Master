package com.github.qichensn.item;

import com.github.qichensn.data.component.IDCardComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.qichensn.register.DataComponentRegister.ID_CARD_COMPONENT;

public class IDCardItem extends Item {
    public IDCardItem(Properties properties) {
        super(properties);
    }

    private static void setMaidUUID(ItemStack stack, String uuid){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            component.setMaidUUID(uuid);
            stack.set(ID_CARD_COMPONENT, component);
        }
    }
    private static void setBePos(ItemStack stack, BlockPos pos){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            component.setBePos(pos);
            stack.set(ID_CARD_COMPONENT, component);
        }
    }
    private static String getMaidUUID(ItemStack stack){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            return component.getMaidUUID();
        }
        return null;
    }
    private static BlockPos getBePos(ItemStack stack){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            return component.getBePos();
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
            player.displayClientMessage(Component.literal("成功绑定到方块实体!"+getBePos(context.getItemInHand())), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        String pos = getBePos(stack) == null ? "null" : getBePos(stack).toString();
        String uuid = getMaidUUID(stack) == null ? "null" : getMaidUUID(stack);
        tooltipComponents.add(Component.translatable("tooltip.id_card_be",pos));
        tooltipComponents.add(Component.translatable("tooltip.id_card_maid",uuid));
    }
}
