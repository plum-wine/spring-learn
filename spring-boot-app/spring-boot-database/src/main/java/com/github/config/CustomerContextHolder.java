package com.github.config;

/**
 * @author hangs.zhang
 * @date 2020/02/19 18:37
 * *****************
 * function:
 */
public class CustomerContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCustomerType(String type) {
        contextHolder.set(type);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void cleatCustomerType() {
        contextHolder.remove();
    }

}
