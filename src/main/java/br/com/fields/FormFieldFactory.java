package br.com.fields;

public class FormFieldFactory {
	private FormFieldFactory() {}

	public static FormField create(Field field) {

		switch (field.getType()) {

		case "date":
			return new DateFormField(field.getName());

		case "combo":
			return new ComboFormField(field.getName(), field.getOptions());

		default:
			return new TextFormField(field.getName());
		}
	}
}
