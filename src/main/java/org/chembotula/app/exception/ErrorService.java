package org.chembotula.app.exception;

import java.io.Serializable;

public class ErrorService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String error;
	private String path;
	private int codeStatus;
	
	public ErrorService() {}
	
	public ErrorService(String error, String path, int codeStatus) {
		this.error = error;
		this.path = path;
		this.codeStatus = codeStatus;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getCodeStatus() {
		return codeStatus;
	}
	
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
}
