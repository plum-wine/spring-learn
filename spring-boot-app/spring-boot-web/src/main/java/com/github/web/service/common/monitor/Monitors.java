package com.github.web.service.common.monitor;

/**
 * @author hangs.zhang
 * @date 19-7-24 下午2:57
 * *********************
 * function:
 */
public enum Monitors implements MRegistry {
    /**
     * 服务器内部异常
     */
    inner_error("服务器内部异常");

    private final String desc;

    Monitors(String desc) {
        this.desc = desc;
    }


    @Override
    public String key() {
        return name();
    }

    @Override
    public String desc() {
        return desc;
    }
}
