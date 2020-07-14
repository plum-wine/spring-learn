package com.github.foobar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.BitSet;

/**
 * @author hangs.zhang
 * @date 2020/07/12 22:23
 * *****************
 * function:
 */
@Service
public class FoobarService implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getValue(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

}
