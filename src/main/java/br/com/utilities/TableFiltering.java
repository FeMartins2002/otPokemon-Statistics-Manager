package br.com.utilities;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableFiltering {
	private TableFiltering() {}

	public static void SearchBarConfiguration(DefaultTableModel model, JTable table, JTextField searchBar) {
		TableRowSorter<DefaultTableModel> filter;

		filter = new TableRowSorter<>(model);
		table.setRowSorter(filter);

		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			private void updateFilter() {
				String text = searchBar.getText();
				if (text.trim().length() == 0) {
					filter.setRowFilter(null);
				} else {
					filter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			public void insertUpdate(DocumentEvent e) {
				updateFilter();
			}

			public void removeUpdate(DocumentEvent e) {
				updateFilter();
			}

			public void changedUpdate(DocumentEvent e) {
				updateFilter();
			}
		});
	}
}
