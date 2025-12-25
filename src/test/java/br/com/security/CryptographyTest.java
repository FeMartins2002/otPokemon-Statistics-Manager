package br.com.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CryptographyTest {

	@Test
	void generateHashTest() {
		String hash = Cryptography.generateHash("password");
		assertEquals(hash, Cryptography.generateHash("password"));
	}

	@Test
	void generateDifferentHashForDifferentInputs() {
		String hash1 = Cryptography.generateHash("password123");
		String hash2 = Cryptography.generateHash("password321");
		assertNotEquals(hash1, hash2);
	}

	@Test
	void generateHashForEmptyString() {
		String hash = Cryptography.generateHash("");
		assertNotNull(hash);
		assertFalse(hash.isEmpty());
	}

	@Test
	void generateHashShouldThrowExceptionForNull() {
		assertThrows(NullPointerException.class, () -> Cryptography.generateHash(null));
	}

	@Test
	void generateHashShouldHaveFixedLength() {
		String hash = Cryptography.generateHash("password");
		assertEquals(64, hash.length());
	}
}
