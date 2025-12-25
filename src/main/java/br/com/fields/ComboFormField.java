package br.com.fields;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import br.com.view.UIBuilder;

public class ComboFormField implements FormField {
	private String name;
	private JComboBox<String> combo;

	public ComboFormField(String name, String[] options) {
		this.name = name;
		this.combo = UIBuilder.createComboField(options);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return combo.getSelectedItem().toString();
	}

	@Override
	public JComponent getComponent() {
		return combo;
	}

	@Override
	public void setValue(String value) {
		combo.setSelectedItem(value);
	}
}
