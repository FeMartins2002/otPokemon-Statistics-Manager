package br.com.fields;

public class Field {
	private String name;
	private String type;
	private String[] options;

	public Field(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Field(String name, String type, String[] options) {
		this.name = name;
		this.type = type;
		this.options = options;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String[] getOptions() {
		return options;
	}
}
