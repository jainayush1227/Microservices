package com.springboot.rest.application.exceptions;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD(
			"Missing Required Fields!!.Please Check Documentations for required fields"), RECORD_ALREADY_EXISTS(
					"Record Already Exists"), INTERNAL_SERVER_ERROR("Internal Server Error"), NO_RECORD_FOUND(
							"Record with provided id is not found"), AUTHENTICATION_FAILURE(
									"Authentication Failed"), COULD_NOT_UPDATE_RECORD(
											"Could Not Update Records"), COULD_NOT_DELETE_RECORD(
													"Could Not Delete Records"), EMIL_ADDRESS_NOT_VERIFIED(
															"Email Address Cannot Be Verified");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
