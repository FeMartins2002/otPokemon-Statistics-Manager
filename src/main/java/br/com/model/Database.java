package br.com.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Database {
	public static final String TECHNICAL_MACHINE = "technical_machine";
	public static final String HORDE_LEADER = "horde_leader";
	public static final String MIGRATORYS = "migratorys";
	public static final String SHINYS = "shinys";

	public static int getTotalColumns(String tableName) {
		if(tableName.equals(TECHNICAL_MACHINE)) {
			return 3;
		}
		else if(tableName.equals(HORDE_LEADER) || tableName.equals(MIGRATORYS)) {
			return 5;
		}
		else {
			return 2;
		}
	}

	public static String getNameColumn(String tableName) {
		if(tableName.equals(TECHNICAL_MACHINE)) {
			return "Type";
		}
		else if(tableName.equals(HORDE_LEADER) || tableName.equals(MIGRATORYS)) {
			return "Pokeball";
		}
		else {
			return "Name";
		}
	}

	public static Vector<String> getColumns(String tableName) {
		return new Vector<>(COLUMNS.getOrDefault(tableName, List.of()));
	}

	private static final Map<String, List<String>> COLUMNS = Map.of(
			TECHNICAL_MACHINE, Arrays.asList("id", "date", "name", "type", "pokemon", "droop"),
			HORDE_LEADER, Arrays.asList("id", "date", "name", "gender", "nature", "pokeball", "location"),
			MIGRATORYS, Arrays.asList("id", "date", "name", "gender", "nature", "pokeball", "location"),
			SHINYS, Arrays.asList("id", "date", "name", "location", "capture"));
}
