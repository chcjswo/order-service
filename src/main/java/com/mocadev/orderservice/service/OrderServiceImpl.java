package com.mocadev.orderservice.service;

import com.mocadev.orderservice.dto.OrderDto;
import com.mocadev.orderservice.jpa.OrderEntity;
import com.mocadev.orderservice.jpa.OrderRepository;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-26
 **/
@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		orderDto.setOrderId(UUID.randomUUID().toString());
		orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		OrderEntity userEntity = mapper.map(orderDto, OrderEntity.class);

		orderRepository.save(userEntity);

		return mapper.map(userEntity, OrderDto.class);
	}

	@Override
	public OrderDto getOrdersByOrderId(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(orderEntity, OrderDto.class);
	}

	@Override
	public Iterable<OrderEntity> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}

}
