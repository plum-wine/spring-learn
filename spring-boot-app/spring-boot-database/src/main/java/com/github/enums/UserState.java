package com.github.enums;

public enum UserState implements BaseOrderEnum<UserState> {
    ACTIVE(1, "ACTIVE"),
    BLOCK(-1, "BLOCK");

    private final Integer order;

    private final String desc;

    UserState(Integer order, String desc) {
        this.order = order;
        this.desc = desc;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    static {
        SUB_CLASS.add(UserState.class);
    }

}
