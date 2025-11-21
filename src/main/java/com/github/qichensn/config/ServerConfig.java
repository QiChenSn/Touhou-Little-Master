package com.github.qichensn.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static final ModConfigSpec SPEC = BUILDER.build();


    public static ModConfigSpec init(){
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("东方小主人服务器配置");

        return builder.build();
    }
}
