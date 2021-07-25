package com.mocadev.orderservice.service;


import com.mocadev.orderservice.dto.OrderDto;
import com.mocadev.orderservice.jpa.OrderEntity;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-26
 **/
public interface OrderService {

	OrderDto createOrder(OrderDto orderDto);
	OrderDto getOrdersByOrderId(String orderId);
	Iterable<OrderEntity> getOrdersByUserId(String userId);

}
