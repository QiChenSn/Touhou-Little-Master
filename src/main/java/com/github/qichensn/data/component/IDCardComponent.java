package com.github.qichensn.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.core.BlockPos;

public record IDCardComponent(String maidUUID, BlockPos bePos) {
    public static final StreamCodec<ByteBuf, IDCardComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, IDCardComponent::maidUUID,
            BlockPos.STREAM_CODEC, IDCardComponent::bePos,
            IDCardComponent::new
    );

    public static final Codec<IDCardComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("uuid").forGetter(IDCardComponent::maidUUID),
                    BlockPos.CODEC.fieldOf("pos").forGetter(IDCardComponent::bePos)
            ).apply(instance, IDCardComponent::new)
    );
}