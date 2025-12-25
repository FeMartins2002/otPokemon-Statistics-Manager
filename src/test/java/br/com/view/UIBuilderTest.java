package br.com.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class UIBuilderTest {

	@Test
	void testCreateLabel() {
		JLabel label = UIBuilder.createLabel(300, 40, "title", Color.BLACK, 18);

		assertNotNull(label);
		assertEquals("title", label.getText());
		assertEquals(300, label.getPreferredSize().width);
		assertEquals(40, label.getPreferredSize().height);
		assertEquals(new Font("Arial", Font.PLAIN, 18), label.getFont());
		assertEquals(Color.BLACK, label.getForeground());
		assertFalse(label.isOpaque());
	}

	@Test
	void testCreationTextField() {
		JTextField field = UIBuilder.createTextField(300, 40);

		assertNotNull(field);
		assertEquals(300, field.getPreferredSize().width);
		assertEquals(40, field.getPreferredSize().height);
		assertEquals(new Font("Arial", Font.PLAIN, 18), field.getFont());
		assertEquals(Color.white, field.getBackground());
	}

	@Test
	void testCreatePasswordField() {
		JPasswordField field = UIBuilder.createPasswordField(300, 40);

		assertNotNull(field);
		assertEquals(300, field.getPreferredSize().width);
		assertEquals(40, field.getPreferredSize().height);
		assertEquals(new Font("Arial", Font.PLAIN, 18), field.getFont());
		assertEquals(Color.white, field.getBackground());
	}

	@Test
	void testCreateButton() {
		JButton button = UIBuilder.createButton(300, 40, Color.WHITE, "button", 18);

		assertNotNull(button);
		assertEquals("button", button.getText());
		assertEquals(Color.white, button.getForeground());
		assertEquals(300, button.getPreferredSize().width);
		assertEquals(40, button.getPreferredSize().height);
		assertEquals(new Font("Arial", Font.PLAIN, 18), button.getFont());
		assertEquals(Color.white, button.getBackground());
		assertTrue(button.isOpaque());
	}

	@Test
	void testCreateCheckBox() {
		JCheckBox checkBox = UIBuilder.createCheckBox("check");

		assertNotNull(checkBox);
		assertEquals("check", checkBox.getText());
		assertEquals(Color.white, checkBox.getForeground());
		assertEquals(new Font("Arial", Font.PLAIN, 14), checkBox.getFont());
		assertFalse(checkBox.isOpaque());
	}
}
