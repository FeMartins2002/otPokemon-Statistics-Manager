package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest {
	private Field fieldWithOptions;
	private Field fieldWithoutOptions;

	@BeforeEach
	void setUp() {
		fieldWithoutOptions = new Field("Name", "Text");
		fieldWithOptions = new Field("Gender", "Text", new String[]{"Male", "Female"});
	}

	@Test
	void testFieldWithOptions() {
		assertNotNull(fieldWithOptions);
		assertEquals("Gender", fieldWithOptions.getName());
		assertEquals("Text", fieldWithOptions.getType());
		assertArrayEquals(new String[]{"Male", "Female"}, fieldWithOptions.getOptions());
	}

	@Test
	void testFieldWithoutOptions() {
		assertNotNull(fieldWithoutOptions);
		assertEquals("Name", fieldWithoutOptions.getName());
		assertEquals("Text", fieldWithoutOptions.getType());
		assertNull(fieldWithoutOptions.getOptions(), "Options should be null when not provided.");
	}
}
