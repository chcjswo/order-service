package com.mocadev.orderservice.controller;

import com.mocadev.orderservice.dto.OrderDto;
import com.mocadev.orderservice.jpa.OrderEntity;
import com.mocadev.orderservice.service.OrderService;
import com.mocadev.orderservice.vo.RequestOrder;
import com.mocadev.orderservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-27
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

	private final Environment env;
	private final OrderService orderService;

	@GetMapping("/health-check")
	public String status() {
		return String.format("It's Working in Catalog Service on PORT %s",
			env.getProperty("local.server.port"));
	}

	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrder> createUser(@PathVariable String userId,
													@RequestBody RequestOrder requestOrder) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
		orderDto.setUserId(userId);
		OrderDto order = orderService.createOrder(orderDto);
		ResponseOrder responseUser = mapper.map(order, ResponseOrder.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getCatalogs(@PathVariable String userId) {
		Iterable<OrderEntity> catalogs = orderService.getOrdersByUserId(userId);
		List<ResponseOrder> result = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();

		catalogs.forEach(v -> result.add(mapper.map(v, ResponseOrder.class)));

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
