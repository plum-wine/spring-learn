package com.github.entity;

import lombok.Data;

@Data
public class OrderDO {

    private Long orderId;

    private String orderName;

    private Integer orderStatus;

    private Long userId;

}
