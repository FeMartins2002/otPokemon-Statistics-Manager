package br.com.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JFormattedTextField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateFormFieldTest {
    private DateFormField dateFormField;

    @BeforeEach
    void setUp() {
        dateFormField = new DateFormField("testDateField");
    }

    @Test
    void testConstructor() {
        assertNotNull(dateFormField);
        assertEquals("testDateField", dateFormField.getName());
    }

    @Test
    void testGetValue() {
        dateFormField.setValue("12/12/2012");
        assertEquals("12/12/2012", dateFormField.getValue());
    }
    
    @Test
    void testGetComponent() {
        JFormattedTextField component = (JFormattedTextField) dateFormField.getComponent();
        assertNotNull(component);
    }
}
