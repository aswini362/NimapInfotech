package com.nimapinfotech.tech.validation;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nimapinfotech.tech.RequestDTO.CategoryRequest;


public class CategoryRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return CategoryRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryRequest request = (CategoryRequest) target;
		if (checkInputString(request.getName())) {
			errors.rejectValue("name", "name.empty");
		}

		if (checkInputString(request.getDescription())) {
			errors.rejectValue("description", "description.empty");
		}
		

	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}

	
}
