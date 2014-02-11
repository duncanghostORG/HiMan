package com.ww.exceptions;

public class BizException extends Exception {
	public BizException(Exception e) {
		super(e);
	}

	public BizException(Exception e, String str) {
		super(str,e);
	}
}
