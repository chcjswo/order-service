package com.mocadev.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-26
 **/
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

	OrderEntity findByOrderId(String orderId);
	Iterable<OrderEntity> findByUserId(String userId);

}
