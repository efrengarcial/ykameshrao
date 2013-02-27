package com.yourpackagename.framework.response;

import java.util.List;

public class ValidationResponse {
	private String status;
	private List<String> errorMessageList;
	private String redirect ;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getErrorMessageList() {
		return this.errorMessageList;
	}
	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
}
