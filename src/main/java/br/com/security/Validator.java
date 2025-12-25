package br.com.security;

import java.util.Map;
import java.util.Map.Entry;

public class Validator {
	private Validator() {}

	public static boolean validateString(String text) {
		if (text == null || text.isEmpty() || text.isBlank()) {
			return false;
		}
		return true;
	}

	public static boolean validateNumber(int number) {
		if(number < 0) {
			return false;
		}
		return true;
	}

	public static boolean validateMap(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return false;
		}

		for (Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey() == null || entry.getValue() == null) {
				return false;
			}

			if (entry.getKey().isEmpty() || entry.getValue().isEmpty()) {
				return false;
			}

			if (entry.getKey().isBlank() || entry.getValue().isBlank()) {
				return false;
			}
		}
		return true;
	}
}
