package com.mocadev.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.Data;

/**
 * @author chcjswo
 * @version 1.0.0
 * @blog https://mocadev.tistory.com
 * @github https://github.com/chcjswo
 * @since 2021-07-26
 **/
@Data
@JsonInclude(Include.NON_NULL)
public class ResponseOrder {

	private String productId;
	private Integer unitPrice;
	private Integer totalPrice;
	private Integer qty;
	private Date createdAt;
	private String orderId;

}
