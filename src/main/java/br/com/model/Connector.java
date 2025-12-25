package br.com.model;

public class Connector {
	private static final String URL = "jdbc:sqlite:data/otPokemonDB.db";

	private Connector() {}

	public static String getUrl() {
		return URL;
	}
}
