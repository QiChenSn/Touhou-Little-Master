package com.github.qichensn.item;

import com.github.qichensn.data.component.IDCardComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.units.qual.C;

import static com.github.qichensn.register.DataComponentRegister.ID_CARD_COMPONENT;

public class IDCardItem extends Item {
    public IDCardItem(Properties properties) {
        super(properties);
    }

    private static void addMaidUUID(ItemStack stack, String uuid){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            component.setMaidUUID(uuid);
            stack.set(ID_CARD_COMPONENT, component);
        }
    }
    private static void addBeUUID(ItemStack stack, String uuid){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            component.setBeUUID(uuid);
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
    private static String getBeUUID(ItemStack stack){
        IDCardComponent component = stack.get(ID_CARD_COMPONENT);
        if (component != null) {
            return component.getBeUUID();
        }
        return null;
    }
}
