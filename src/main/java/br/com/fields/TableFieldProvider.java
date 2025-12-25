package br.com.fields;

import java.util.ArrayList;
import java.util.List;

import br.com.model.Database;

public class TableFieldProvider {
	private TableFieldProvider() {}

	public static List<Field> getFields(String tableName) {
		List<Field> fields = new ArrayList<>();

		switch (tableName) {

		case Database.TECHNICAL_MACHINE:
			fields = firstGroupFields();
			break;

		case Database.HORDE_LEADER:
			fields = secondGroupFields();
			break;

		case Database.MIGRATORYS:
			fields = secondGroupFields();
			break;

		case Database.SHINYS:
			fields = thirdGroupFields();
			break;
		}
		return fields;
	}

	private static List<Field> firstGroupFields() {
		List<Field> fields = new ArrayList<>();

		fields.add(new Field("Date", "date"));
		fields.add(new Field("Name", "text"));
		fields.add(new Field("Type", "combo", new String[] {
				"Fire", "Water", "Dark", "Fairy", "Poison", "Bug", "Electric","Normal", "Ice", "Dragon", "Fighting", 
				"Flying", "Ghost","Grass", "Ground", "Psychic", "Rock", "Steel"}));
		fields.add(new Field("Pokemon", "text"));
		fields.add(new Field("Droop", "text"));

		return fields;
	}

	private static List<Field> secondGroupFields() {
		List<Field> fields = new ArrayList<>();

		fields.add(new Field("Date", "date"));
		fields.add(new Field("Name", "text"));
		fields.add(new Field("Gender", "combo", new String[] {"Male", "Felame"}));
		fields.add(new Field("Nature", "combo", new String[] {"Hardy", "Lonely", "Brave", "Adamant", "Naughty", "Bold", "Docile", "Relaxed", "Impish", "Lax", 
				"Timid", "Hasty", "Serious", "Jolly", "Naive","Modest", "Mild", "Quiet", "Bashful", "Rash", "Calm", "Gentle", "Sassy", "Careful", "Quirky"}));
		fields.add(new Field("Pokeball", "combo", new String[] {"Pokeball", "Greatball", "Premierball", "Ultraball", "Levelball", "Loveball", "Diveball", 
				"Duskball", "Luxuryball", "Repeatball", "Heavyball", "Netball", "Healball", "Oldball", "Beastball"}));
		fields.add(new Field("Location", "text"));

		return fields;
	}

	private static List<Field> thirdGroupFields() {
		List<Field> fields = new ArrayList<>();

		fields.add(new Field("Date", "date"));
		fields.add(new Field("Name", "text"));
		fields.add(new Field("Location", "text"));
		fields.add(new Field("Capture", "combo", new String[]{"True", "False"}));

		return fields;
	}
}
