package com.github.web.exceptions.context;

/**
 * @author hangs.zhang
 * @date 2018/7/30
 * *********************
 * function: 快照上下文
 */
public class SnapshotContext {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void set(Object snap) {
        threadLocal.set(snap);
    }

    public static Object get() {
        Object result = threadLocal.get();
        // 防止ThreadLocal引发内存泄露
        threadLocal.remove();
        return result;
    }

}
