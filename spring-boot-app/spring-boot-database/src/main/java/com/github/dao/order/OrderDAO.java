package com.github.dao.order;

import com.github.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDAO {

    /**
     * @param condition MyBatis支持Map格式的condition
     * @return List<OrderDO>
     */
    List<OrderDO> selectAllByCondition(@Param("condition") Map<String, Object> condition);

    int insert(@Param("order") OrderDO orderDO);

}
