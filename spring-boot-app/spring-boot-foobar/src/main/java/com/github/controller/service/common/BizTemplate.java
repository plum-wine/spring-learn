package com.github.controller.service.common;

import com.github.controller.service.common.monitor.M;
import com.github.controller.service.common.monitor.MRegistry;
import com.github.controller.exceptions.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author hangs.zhang
 * @date 19-7-24 下午2:57
 * *********************
 * function:
 */
public abstract class BizTemplate<T> {

    protected static Logger logger = LoggerFactory.getLogger(BizTemplate.class);

    protected MRegistry mRegistry;

    protected BizTemplate(MRegistry mRegistry) {
        this.mRegistry = mRegistry;
    }

    /**
     * 业务处理
     */
    protected abstract T process() throws GlobalException;

    /**
     * 兜底
     */
    protected abstract T defaultData() throws GlobalException;

    /**
     * 参数校验
     */
    protected abstract void checkParams() throws GlobalException;

    protected String monitorQueryKey() {
        return StringUtils.EMPTY;
    }

    /**
     * 失败后的处理
     *
     * @param e
     */
    protected void onFailure(Throwable e) {
        // 监控记录
        // 日志记录
    }

    public T execute() throws GlobalException {
        try {
            checkParams();
            long start = System.currentTimeMillis();
            T result = process();
            M.tq(mRegistry, System.currentTimeMillis() - start);
            return result;
        } catch (GlobalException e) {
            onFailure(e);
            logger.error("error while execute, key = {},query={}", mRegistry.key(), e);
            return defaultData();
        } catch (Exception e) {
            onFailure(e);
            logger.error("error while execute, key = {},query={}", mRegistry.key(), e);
            throw new GlobalException(e.getMessage(), e);
        }
    }

}
