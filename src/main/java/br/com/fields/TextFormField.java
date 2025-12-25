package br.com.fields;

import javax.swing.JComponent;
import javax.swing.JTextField;

import br.com.view.UIBuilder;

public class TextFormField implements FormField {
	private String name;
	private JTextField field;

	public TextFormField(String name) {
		this.name = name;
		this.field = UIBuilder.createTextField(300, 40);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return field.getText();
	}

	@Override
	public JComponent getComponent() {
		return field;
	}

	@Override
	public void setValue(String value) {
		field.setText(value);
	}
}
