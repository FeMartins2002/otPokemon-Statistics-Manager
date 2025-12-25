package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.model.Database;

public class TableFieldProviderTest {

	@Test
	void testGetFieldsForTechnicalMachine() {
		List<Field> fields = TableFieldProvider.getFields(Database.TECHNICAL_MACHINE);

		assertNotNull(fields);
		assertEquals(5, fields.size());

		assertEquals("Date", fields.get(0).getName());
		assertEquals("date", fields.get(0).getType());

		assertEquals("Name", fields.get(1).getName());
		assertEquals("text", fields.get(1).getType());

		assertEquals("Type", fields.get(2).getName());
		assertEquals("combo", fields.get(2).getType());
		assertArrayEquals(new String[] {"Fire", "Water", "Dark", "Fairy", "Poison", "Bug", "Electric", "Normal", "Ice", 
				"Dragon", "Fighting", "Flying", "Ghost", "Grass", "Ground", "Psychic", "Rock", "Steel"
		}, fields.get(2).getOptions());

		assertEquals("Pokemon", fields.get(3).getName());
		assertEquals("text", fields.get(3).getType());

		assertEquals("Droop", fields.get(4).getName());
		assertEquals("text", fields.get(4).getType());
	}

	@Test
	void testGetFieldsForHordeLeaderAndMigratorys() {
		List<Field> fields = TableFieldProvider.getFields(Database.HORDE_LEADER);

		assertNotNull(fields);
		assertEquals(6, fields.size());

		assertEquals("Date", fields.get(0).getName());
		assertEquals("date", fields.get(0).getType());

		assertEquals("Name", fields.get(1).getName());
		assertEquals("text", fields.get(1).getType());

		assertEquals("Gender", fields.get(2).getName());
		assertEquals("combo", fields.get(2).getType());
		assertArrayEquals(new String[] {"Male", "Felame"}, fields.get(2).getOptions());

		assertEquals("Nature", fields.get(3).getName());
		assertEquals("combo", fields.get(3).getType());
		assertArrayEquals(new String[] {"Hardy", "Lonely", "Brave", "Adamant", "Naughty", "Bold", "Docile", "Relaxed", "Impish", "Lax", "Timid", 
				"Hasty", "Serious", "Jolly", "Naive","Modest", "Mild", "Quiet", "Bashful", "Rash", "Calm", "Gentle", "Sassy", "Careful", "Quirky"
		}, fields.get(3).getOptions());

		assertEquals("Pokeball", fields.get(4).getName());
		assertEquals("combo", fields.get(4).getType());
		assertArrayEquals(new String[] {"Pokeball", "Greatball", "Premierball", "Ultraball", "Levelball", "Loveball", 
				"Diveball", "Duskball", "Luxuryball", "Repeatball", "Heavyball", "Netball", "Healball", "Oldball", "Beastball"
		}, fields.get(4).getOptions());

		assertEquals("Location", fields.get(5).getName());
		assertEquals("text", fields.get(5).getType());
	}

	@Test
	void testGetFieldsForShinys() {
		List<Field> fields = TableFieldProvider.getFields(Database.SHINYS);

		assertNotNull(fields);
		assertEquals(4, fields.size());

		assertEquals("Date", fields.get(0).getName());
		assertEquals("date", fields.get(0).getType());

		assertEquals("Name", fields.get(1).getName());
		assertEquals("text", fields.get(1).getType());

		assertEquals("Location", fields.get(2).getName());
		assertEquals("text", fields.get(2).getType());

		assertEquals("Capture", fields.get(3).getName());
		assertEquals("combo", fields.get(3).getType());
		assertArrayEquals(new String[] {"True", "False"}, fields.get(3).getOptions());
	}

	@Test
	void testGetFieldsForUnknownTable() {
		List<Field> fields = TableFieldProvider.getFields("UnknownTable");

		assertNotNull(fields);
		assertEquals(0, fields.size());
	}
}
