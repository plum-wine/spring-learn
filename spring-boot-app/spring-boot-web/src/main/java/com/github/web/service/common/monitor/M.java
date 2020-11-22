package com.github.web.service.common.monitor;


/**
 * @author hangs.zhang
 * @date 19-7-24 下午2:57
 * *********************
 * function:
 */
public class M {


    /**
     * time
     *
     * @param mRegistry
     * @param time
     */
    public static void time(MRegistry mRegistry, long time) {
        // TODO: 19-7-24 记录时间
    }


    /**
     * qps
     *
     * @param mRegistry
     */
    public static void qps(MRegistry mRegistry) {
        // TODO: 19-7-24 记录qps
    }

    public static void qps(String target) {
        // TODO: 19-7-24 记录qps
    }

    public static void deltaCounter(MRegistry mRegistry) {
        // TODO: 19-7-24 记录次数
    }

    public static void deltaCounter(String target) {
        // TODO: 19-7-24 记录次数
    }

    public static void tq(MRegistry mRegistry, long time) {
        M.time(mRegistry, time);
        M.qps(mRegistry);
    }


}
