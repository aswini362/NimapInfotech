package com.nimapinfotech.tech.RequestDTO;

import java.util.List;

import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
	@NotBlank(message = "'name' must not be empty")
	private String name;
	@NotBlank(message = "'description' must not be empty")
	private String description;

	
	
	private List<ProductRequest> product;
}
