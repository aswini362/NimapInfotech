package com.nimapinfotech.tech.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private Long id;

	private String productName;

	private Long qty;

	private Long price;
}
