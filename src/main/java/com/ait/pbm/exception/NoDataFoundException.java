package com.ait.pbm.exception;

public class NoDataFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8954946784780305962L;
	
	@SuppressWarnings("unused")
	private String msg;

	public NoDataFoundException(String msg) {
		super(msg);
	}
	

}
