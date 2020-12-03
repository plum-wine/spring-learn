package com.github.controller.exceptions.context;

/**
 * @author hangs.zhang
 * @date 2018/7/30
 * *********************
 * function: 快照上下文
 */
public class SnapshotContext {

    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    private SnapshotContext() {
    }

    public static void set(Object snap) {
        THREAD_LOCAL.set(snap);
    }

    public static Object get() {
        Object result = THREAD_LOCAL.get();
        // 防止ThreadLocal引发内存泄露
        THREAD_LOCAL.remove();
        return result;
    }

}
