package com.nimapinfotech.tech.RequestDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
	private Long id;
	private String name;
	private String description;

	private List<ProductRequest> product;
}
