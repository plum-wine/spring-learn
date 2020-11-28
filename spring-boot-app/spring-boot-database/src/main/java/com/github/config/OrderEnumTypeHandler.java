package com.github.config;


import com.github.enums.BaseOrderEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author plum-wine
 * @date 2019/10/04 20:46
 * *****************
 * function:
 */
@MappedTypes({BaseOrderEnum.class})
public class OrderEnumTypeHandler<E extends BaseOrderEnum<?>> extends BaseTypeHandler<BaseOrderEnum<?>> {

    private final Class<E> type;

    public OrderEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new RuntimeException("can`t find enum type");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseOrderEnum baseOrderEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, baseOrderEnum.getOrder());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return findEnumByOrder(resultSet.getInt(s));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return findEnumByOrder(resultSet.getInt(i));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return findEnumByOrder(callableStatement.getInt(i));
    }

    private E findEnumByOrder(int order) {
        return BaseOrderEnum.findEnumByOrder(type, order);
    }

}
