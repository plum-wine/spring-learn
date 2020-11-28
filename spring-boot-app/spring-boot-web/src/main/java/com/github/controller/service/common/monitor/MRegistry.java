package com.github.controller.service.common.monitor;


/**
 * @author hangs.zhang
 * @date 19-7-24 下午2:57
 * *********************
 * function:
 */
public interface MRegistry {

    /**
     * 获取名字
     *
     * @return String
     */
    String key();

    /**
     * 获取描述信息
     *
     * @return String
     */
    String desc();

}
