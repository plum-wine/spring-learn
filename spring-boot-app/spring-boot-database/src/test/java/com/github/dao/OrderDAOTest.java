package com.github.dao;

import com.github.SpringBootDatabaseApplicationTests;
import com.github.entity.OrderDO;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderDAOTest extends SpringBootDatabaseApplicationTests {

    @Autowired
    private OrderDAO orderDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void selectAllByCondition() {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("userId", 100L);
        List<OrderDO> orderDOS = orderDAO.selectAllByCondition(condition);
        orderDOS.forEach(System.out::println);
    }

    @Test
    public void insert() {
        Random random = new Random();
        long orderId = Math.abs(random.nextLong());
        long userId = Math.abs(random.nextLong());
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderId(orderId);
        orderDO.setUserId(userId);
        orderDO.setOrderName("order name " + orderId);
        orderDO.setOrderStatus(1);
        orderDAO.insert(orderDO);
        System.out.println(orderDO);
    }

}