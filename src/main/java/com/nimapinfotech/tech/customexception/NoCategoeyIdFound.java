package com.nimapinfotech.tech.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCategoeyIdFound extends RuntimeException {
	private static final long serialVersionUID = -4667620469770761205L;

	public NoCategoeyIdFound(String msg) {
		super(msg);
	}
}
