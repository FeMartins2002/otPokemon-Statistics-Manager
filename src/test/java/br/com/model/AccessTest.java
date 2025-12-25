package br.com.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class AccessTest {
	private String username = "admin";
	private String password = "123";
	private String response = "abc";

	@Test
	void itShouldReturnFalseForInvalidInputs() {
		assertFalse(Access.userValidate("", ""));
		assertFalse(Access.userValidate(" ", " "));
		assertFalse(Access.userValidate(username, password));
	}

	@Test
	void itShouldReturnFalseForAnIncorrectAnswer() {
		assertFalse(Access.validateResponse(""));
		assertFalse(Access.validateResponse(" "));
		assertFalse(Access.validateResponse(response));
	}
}
