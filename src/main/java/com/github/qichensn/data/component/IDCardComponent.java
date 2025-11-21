package com.github.qichensn.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;
import java.util.UUID;


public class IDCardComponent {
    public String maidUUID;
    public String beUUID;

    public IDCardComponent(String maidUUID, String beUUID) {
        this.maidUUID = maidUUID;
        this.beUUID = beUUID;
    }

    public String getMaidUUID() {
        return maidUUID;
    }

    public void setMaidUUID(String maidUUID) {
        this.maidUUID = maidUUID;
    }

    public String getBeUUID() {
        return beUUID;
    }

    public void setBeUUID(String beUUID) {
        this.beUUID = beUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        IDCardComponent that = (IDCardComponent) o;
        
        if (!Objects.equals(maidUUID, that.maidUUID)) return false;
        return Objects.equals(beUUID, that.beUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maidUUID, beUUID);
    }

    public static final StreamCodec<ByteBuf, IDCardComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, IDCardComponent::getMaidUUID,
            ByteBufCodecs.STRING_UTF8, IDCardComponent::getBeUUID,
            IDCardComponent::new
    );

}
