package com.github;

import com.github.entity.OrderDO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class ShardingSphereTest extends SpringBootDatabaseApplicationTests {

    @Autowired
    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

        String sql = "insert into t_order(order_id, order_name, order_status, user_id) values (?, ?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, orderDO.getOrderId());
            preparedStatement.setString(2, orderDO.getOrderName());
            preparedStatement.setInt(3, orderDO.getOrderStatus());
            preparedStatement.setLong(4, orderDO.getUserId());
            int count = preparedStatement.executeUpdate();
            LOGGER.info("count : {}", count);
            LOGGER.info("user : {}", orderDO);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
    }

    @Test
    public void select() {
        String sql = "select * from t_order";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    LOGGER.info("userId:{}, orderId:{}", rs.getString("user_id"), rs.getString("order_id"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
    }

}
