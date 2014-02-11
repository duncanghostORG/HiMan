package com.ww.exceptions;

public class DAOException extends Exception {
	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(Exception e, String str) {
		super(str,e);
	}
}
