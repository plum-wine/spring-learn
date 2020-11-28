package com.github.config;


import com.github.constant.Constants;

import java.util.Optional;

/**
 * @author hangs.zhang
 * @date 2020/02/19 18:37
 * *****************
 * function:
 */
public class CustomerDatabaseContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setCustomerType(String type) {
        CONTEXT_HOLDER.set(type);
    }

    public static String getCustomerType() {
        return Optional.ofNullable(CONTEXT_HOLDER.get()).orElse(Constants.DEFAULT_DATABASE_INSTANCE);
    }

    public static void cleatCustomerType() {
        CONTEXT_HOLDER.remove();
    }

}
