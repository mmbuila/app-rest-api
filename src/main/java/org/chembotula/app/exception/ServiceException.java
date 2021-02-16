package org.chembotula.app.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int codeStatus;
	
	public ServiceException(int codeStatus, String message) {
		super(message);
		this.codeStatus = codeStatus;
	}

	public int getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
}
