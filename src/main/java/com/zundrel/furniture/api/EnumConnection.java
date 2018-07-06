package com.zundrel.furniture.api;

import net.minecraft.util.IStringSerializable;

public enum EnumConnection implements IStringSerializable {
    SAME("same"),
    TYPE("type"),
    OPAQUE("opaque");

    private String name;

    private EnumConnection(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
