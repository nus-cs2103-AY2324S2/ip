package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import task.Priority;

/**
 * Tests for the EnumConverter class.
 */
public class EnumConverterTest {

    /**
     * Tests the conversion of a string to a priority enum.
     */
    @Test
    public void testConvertStringToPriority() {
        assertEquals(Priority.HIGH, EnumConverter.convertStringToPriority("high"));
        assertEquals(Priority.MEDIUM, EnumConverter.convertStringToPriority("medium"));
        assertEquals(Priority.LOW, EnumConverter.convertStringToPriority("low"));
        assertEquals(Priority.NONE, EnumConverter.convertStringToPriority("none"));
    }

    /**
     * Tests the conversion of an invalid string to a priority enum.
     */
    @Test
    public void convertStringToPriority_invalidInput_exceptionThrown() {
        try {
            EnumConverter.convertStringToPriority("invalid");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    /**
     * Tests the conversion of a priority enum to a string for file storage.
     */
    @Test
    public void convertPriorityToFileString_correctInput() {
        assertEquals("high", EnumConverter.convertPriorityToFileString(Priority.HIGH));
        assertEquals("medium", EnumConverter.convertPriorityToFileString(Priority.MEDIUM));
        assertEquals("low", EnumConverter.convertPriorityToFileString(Priority.LOW));
        assertEquals("none", EnumConverter.convertPriorityToFileString(Priority.NONE));
    }

    /**
     * Tests the conversion of a priority enum to a string.
     */
    @Test
    public void convertPriorityToString_correctInput() {
        assertEquals("High", EnumConverter.convertPriorityToString(Priority.HIGH));
        assertEquals("Medium", EnumConverter.convertPriorityToString(Priority.MEDIUM));
        assertEquals("Low", EnumConverter.convertPriorityToString(Priority.LOW));
        assertEquals("None", EnumConverter.convertPriorityToString(Priority.NONE));
    }
}
