package br.com.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Backup {
	private static String errorMessage;

	private Backup() {}

	public static File openSaveWindow(JFrame parentFrame) {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Save copy of database");
		fileChooser.setSelectedFile(new File("backup_otPokemonDB.db"));

		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			if (!selectedFile.getName().toLowerCase().endsWith(".db")) {
				selectedFile = new File(selectedFile.getAbsolutePath() + ".db");
			}
			return selectedFile;
		} else {
			return null;
		}
	}

	public static boolean saveCopyOfBank(String pathOrigin, File destination) {
		try {
			Path origin = Paths.get(pathOrigin);
			Path finalDestination = destination.toPath();

			Files.copy(origin, finalDestination, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException error) {
			errorMessage = error.getMessage();
			JOptionPane.showMessageDialog(null, "Error saving copy\n" + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static String getError() {
		return errorMessage;
	}
}
