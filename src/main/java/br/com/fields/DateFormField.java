package br.com.fields;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import br.com.view.UIBuilder;

public class DateFormField implements FormField {
	private String name;
	private JFormattedTextField field;

	public DateFormField(String name) {
		this.name = name;
		this.field = UIBuilder.createDateField();
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
