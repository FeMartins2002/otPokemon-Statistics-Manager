package br.com.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	@Test
	void shouldReturnFalseWhenStringIsNullOrEmptyOrBlank() {
		String empty = "";
		String nu = null;
		String blank = " ";
		String validText = "admin";

		assertFalse(Validator.validateString(empty));
		assertFalse(Validator.validateString(nu));
		assertFalse(Validator.validateString(blank));
		assertTrue(Validator.validateString(validText));
	}

	@Test
	void shouldReturnTrueWhenNumberIsValid() {
		int validNumber = 1;
		int negativeNumber = -1;

		assertTrue(Validator.validateNumber(validNumber));
		assertFalse(Validator.validateNumber(negativeNumber));
	}

	@Test
	void shouldReturnFalseWhenMapIsNullOrEmpty() {
		Map<String, String> emptyMap = new HashMap<>();
		Map<String, String> nullMap = null;

		assertFalse(Validator.validateMap(nullMap));
		assertFalse(Validator.validateMap(emptyMap));
	}

	@Test
	void shouldReturnFalseWhenMapContainsNullKeyOrValue() {
		Map<String, String> mapWithNullKey = new HashMap<>();
		mapWithNullKey.put(null, "value");

		Map<String, String> mapWithNullValue = new HashMap<>();
		mapWithNullValue.put("key", null);

		Map<String, String> mapWithBlankValue = new HashMap<>();
		mapWithNullValue.put(" ", " ");

		assertFalse(Validator.validateMap(mapWithNullKey));
		assertFalse(Validator.validateMap(mapWithNullValue));
		assertFalse(Validator.validateMap(mapWithBlankValue));
	}

	@Test
	void shouldReturnTrueWhenMapIsValid() {
		Map<String, String> validMap = new HashMap<>();
		validMap.put("key1", "value1");
		validMap.put("key2", "value2");

		assertTrue(Validator.validateMap(validMap));
		assertNotNull(Validator.validateMap(validMap));
	}
}
