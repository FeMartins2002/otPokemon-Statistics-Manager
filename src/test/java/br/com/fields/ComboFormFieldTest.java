package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JComboBox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComboFormFieldTest {
	private ComboFormField comboFormField;

	@BeforeEach
	void setUp() {
		String[] options = {"Adamant", "Lonely", "Calm", "Bold", "Relaxed"};
		comboFormField = new ComboFormField("Nature", options);
	}

	@Test
	void testConstructor() {
		assertNotNull(comboFormField);
		assertEquals("Nature", comboFormField.getName());
	}

	@Test
	void testGetValue() {
		comboFormField.setValue("Calm");
		assertEquals("Calm", comboFormField.getValue());
	}

	@Test
	void testGetComponent() {
		JComboBox component = (JComboBox) comboFormField.getComponent();
		assertNotNull(component);
	}
}
