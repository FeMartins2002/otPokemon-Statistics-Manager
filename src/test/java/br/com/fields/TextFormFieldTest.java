package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JTextField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextFormFieldTest {
	private TextFormField textFormField;

	@BeforeEach
	void setUp() {
		textFormField = new TextFormField("testTextField");
	}

	@Test
	void testConstructor() {
		assertNotNull(textFormField);
		assertEquals("testTextField", textFormField.getName());
	}

	@Test
	void testGetValue() {
		textFormField.setValue("otPokemon");
		assertEquals("otPokemon", textFormField.getValue());
	}

	@Test
	void testGetComponent() {
		JTextField component = (JTextField) textFormField.getComponent();
		assertNotNull(component);
	}
}
