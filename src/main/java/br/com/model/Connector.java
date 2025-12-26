package br.com.model;

public class Connector {
	private static final String URL = "jdbc:sqlite:data/otPokemonDB.db";
	private static final String pathDB = "data/otPokemonDB.db";

	private Connector() {}

	public static String getUrl() {
		return URL;
	}

	public static String getPathdb() {
		return pathDB;
	}
}
