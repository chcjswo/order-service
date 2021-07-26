package com.mocadev.orderservice.vo;

import lombok.Data;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-27
 **/
@Data
public class RequestOrder {

	private String productId;
	private Integer qty;
	private Integer unitPrice;

}
