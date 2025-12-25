package br.com.controller;

import br.com.model.Access;
import br.com.security.Cryptography;
import br.com.security.Validator;

public class AccessController {
	private AccessController() {}

	public static boolean userValidation(String user, String password) {
		if (!validate(user) || !validate(password)) {
			return false;
		}

		String hash = generateHash(password);
		if (!validate(hash)) {
			return false;
		}

		return Access.userValidate(user, hash);
	}

	public static boolean validateResponse(String response) {
		if (!validate(response)) {
			return false;
		}

		String hash = generateHash(response);
		if (!validate(hash)) {
			return false;
		}

		return Access.validateResponse(hash);
	}

	public static boolean changePassword(String password) {
		if (!validate(password)) {
			return false;
		}

		String hash = generateHash(password);
		if (!validate(hash)) {
			return false;
		}

		return Access.changePassword(hash);
	}

	public static boolean changeResponse(String response) {
		if (!validate(response)) {
			return false;
		}

		String hash = generateHash(response);
		if (!validate(hash)) {
			return false;
		}

		return Access.changeResponse(hash);
	}

	private static String generateHash(String text) {
		return Cryptography.generateHash(text);
	}

	private static boolean validate(String text) {
		return Validator.validateString(text);
	}
}
