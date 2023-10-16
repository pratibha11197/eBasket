package com.pratibha.ecommerce.requestresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseHandler {

	    @JsonProperty("success")
	    private boolean success;
	    @JsonProperty("message")
	    private String message;
	    @JsonProperty("result")
	    private Object result;
	    
		public ResponseHandler() {
		}

		public ResponseHandler(boolean success, String message, Object result) {
			this.success = success;
			this.message = message;
			this.result = result;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}
	
}
