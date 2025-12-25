package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormFieldFactoryTest {
	private Field dateField;
	private Field textField;
	private Field comboField;

	@BeforeEach
	void setUp() {
		dateField = new Field("Date", "date");
		textField = new Field("Name", "text");
		comboField = new Field("Nature", "combo", new String[]{"Bold", "Brave", "Docile", "Serious"});
	}

	@Test
	void testCreateDateFormField() {
		FormField formField = FormFieldFactory.create(dateField);

		assertNotNull(formField);
		assertEquals("Date", formField.getName());
		assertTrue(formField instanceof DateFormField);
	}

	@Test
	void testCreateTextFormField() {
		FormField formField = FormFieldFactory.create(textField);

		assertNotNull(formField);
		assertEquals("Name", formField.getName());
		assertTrue(formField instanceof TextFormField);
	}

	@Test
	void testCreateComboFormField() {
		FormField formField = FormFieldFactory.create(comboField);

		assertNotNull(formField);
		assertEquals("Nature", formField.getName());
		assertTrue(formField instanceof ComboFormField);
	}

	@Test
	void testCreateFormFieldWithInvalidType() {
		Field invalidField = new Field("Unknown", "unknown");
		FormField formField = FormFieldFactory.create(invalidField);

		assertNotNull(formField);
		assertEquals("Unknown", formField.getName());
		assertTrue(formField instanceof TextFormField);
	}
}
