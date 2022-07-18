package com.UserMgtSystem.UserMgtSystem.Exception;

public class NameInpuException extends RuntimeException {

	

	public NameInpuException() {
		super("Fields Marked with * are mandatory fields");
		// TODO Auto-generated constructor stub
	}

	public NameInpuException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	

}
