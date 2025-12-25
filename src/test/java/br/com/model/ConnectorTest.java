package br.com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConnectorTest {

	@Test
	void shouldReturnCorrectDatabaseUrl() {
		String expected = "jdbc:sqlite:data/otPokemonDB.db";
		assertEquals(expected, Connector.getUrl());
	}

	@Test
	void shouldNotReturnNull() {
		assertNotNull(Connector.getUrl());
	}

	@Test
	void shouldHaveValidSqliteUrlFormat() {
		String url = Connector.getUrl();

		assertTrue(url.startsWith("jdbc:sqlite:"));
		assertTrue(url.endsWith(".db"));
	}
}
