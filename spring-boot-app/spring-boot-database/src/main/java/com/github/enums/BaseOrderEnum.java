package com.github.enums;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author plum-wine
 * @date 2019/10/04 18:13
 * *****************
 * function:
 */
public interface BaseOrderEnum<E extends Enum<E>> {

    int getOrder();

    String getDesc();

    Set<Class<?>> SUB_CLASS = Sets.newConcurrentHashSet();

    static <E extends BaseOrderEnum<?>> E findEnumByOrder(Class<E> enumClass, int order) {
        E[] enumConstants = enumClass.getEnumConstants();
        E defaultValue = null;
        for (E enumConstant : enumConstants) {
            if (enumConstant.getOrder() == 0) {
                defaultValue = enumConstant;
            }
            if (enumConstant.getOrder() == order) {
                return enumConstant;
            }
        }
        return defaultValue;
    }
}
