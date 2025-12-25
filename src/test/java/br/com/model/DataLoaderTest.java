package br.com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.CategoryDataset;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
	private String invalidTableName = "invalidTable";
	private String invalidColumn = "invalidColumn";

	@Test
	void itShouldReturnAnEmptyDefaultTableModelForAnInvalidTableName() {
		DefaultTableModel result = DataLoader.loadDataFromTable(invalidTableName);

		assertNotNull(result);
		assertEquals(0, result.getRowCount());
		assertEquals(0, result.getColumnCount());
	}

	@Test
	void itShouldReturnAnEmptyDefaultTableModelForAnInvalidTableAndColumnNameSub() {
		DefaultTableModel result = DataLoader.loadDataFromSubTable(invalidTableName, invalidColumn);

		assertNotNull(result);
		assertEquals(0, result.getRowCount());
		assertEquals(0, result.getColumnCount());
	}

	@Test
	void itShouldReturnAnEmptyDefaultTableModelForAnInvalidTableAndColumnNameGraphic() {
		CategoryDataset result = DataLoader.loadDataFromGraphic(invalidTableName, invalidColumn);

		assertNotNull(result);
		assertEquals(0, result.getRowCount());
		assertEquals(0, result.getColumnCount());
	}

	@Test
	void itShouldReturnZeroForAnInvalidTableAndColumnName() {
		String result = DataLoader.loadTotalStatus(invalidTableName);

		assertEquals("0", result);
	}
}
