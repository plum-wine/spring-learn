package com.github.controller.service;

import com.github.controller.exceptions.GlobalException;
import com.github.controller.service.common.BizTemplate;
import com.github.controller.service.common.monitor.Monitors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author hangs.zhang
 * @date 19-7-24 下午2:57
 * *********************
 * function:
 */
@Service
public class FoobarBiz {

    public String bizMethod(final String data) {
        return new BizTemplate<String>(Monitors.inner_error) {
            @Override
            protected String process() throws GlobalException {
                return "deal:" + data;
            }

            @Override
            protected String defaultData() throws GlobalException {
                return "default data";
            }

            @Override
            protected void checkParams() throws GlobalException {
                if (StringUtils.isBlank(data)) {
                    throw new GlobalException("param check error, data is null");
                }
            }
        }.execute();
    }

}
