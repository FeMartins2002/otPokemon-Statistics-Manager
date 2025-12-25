package br.com.fields;

import javax.swing.JComponent;

public interface FormField {
    String getName();
    String getValue();
    JComponent getComponent();
    void setValue(String value);
}
