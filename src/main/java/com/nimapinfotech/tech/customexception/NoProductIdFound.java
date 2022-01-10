package com.nimapinfotech.tech.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductIdFound extends RuntimeException {
	private static final long serialVersionUID = -6509176039589282158L;

	public NoProductIdFound(String msg) {
		super(msg);
	}
}
