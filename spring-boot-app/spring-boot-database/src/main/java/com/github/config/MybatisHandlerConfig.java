package com.github.config;


import com.github.enums.BaseOrderEnum;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author plum-wine
 * @date 2019/10/04 20:46
 * *****************
 * function:
 */
@Component
public class MybatisHandlerConfig implements ConfigurationCustomizer {

    @Override
    public void customize(Configuration configuration) {
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(BaseOrderEnum.class, OrderEnumTypeHandler.class);
    }

}
