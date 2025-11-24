package com.github.qichensn.data.component;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;


public class IDCardComponent {
    public String maidUUID;
    public BlockPos bePos;

    public IDCardComponent(String maidUUID, BlockPos bePos) {
        this.maidUUID = maidUUID;
        this.bePos = bePos;
    }

    public IDCardComponent() {
        this.maidUUID = null;
        this.bePos = null;
    }

    public String getMaidUUID() {
        return maidUUID;
    }

    public void setMaidUUID(String maidUUID) {
        this.maidUUID = maidUUID;
    }

    public BlockPos getBePos() {
        return bePos;
    }

    public void setBePos(BlockPos bePos) {
        this.bePos = bePos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        IDCardComponent that = (IDCardComponent) o;
        
        if (!Objects.equals(maidUUID, that.maidUUID)) return false;
        return Objects.equals(bePos, that.bePos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maidUUID, bePos);
    }

    public static final StreamCodec<ByteBuf, IDCardComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, IDCardComponent::getMaidUUID,
            BlockPos.STREAM_CODEC, IDCardComponent::getBePos,
            IDCardComponent::new
    );

}
