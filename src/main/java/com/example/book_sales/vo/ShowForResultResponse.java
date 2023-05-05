package com.example.book_sales.vo;

import java.util.List;

public class ShowForResultResponse {

	private List<ShowForResult> showForResult;
	
	private String message;
	
	public ShowForResultResponse() {
		
	}

	public ShowForResultResponse(List<ShowForResult> showForResult, String message) {
		super();
		this.showForResult = showForResult;
		this.message = message;
	}
	
	public ShowForResultResponse(String message) {
		super();
		this.message = message;
	}

	public List<ShowForResult> getShowForResult() {
		return showForResult;
	}

	public void setShowForResult(List<ShowForResult> showForResult) {
		this.showForResult = showForResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
