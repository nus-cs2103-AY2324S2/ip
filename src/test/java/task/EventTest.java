package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;

/**
 * Tests for the Event class.
 */
public class EventTest {

    /**
     * Tests the creation of an event task from user input.
     * <p>
     * The input string is in the correct format and the event task is created
     * successfully.
     */
    @Test
    public void eventToString_correctFormat_correctOutput() {
        String eventStartDate = "2024-01-01";
        String eventEndDate = "2024-01-02";
        String eventDescription = "Event Test";
        String inputString = "event " + eventDescription + " /from " + eventStartDate + " /to " + eventEndDate;
        try {
            Event newEvent = Event.createFromInput(inputString);
            String expected = "[E][ ] Event Test (from: Jan 01 2024 to: Jan 02 2024)";
            assertEquals(expected, newEvent.toString());
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    /**
     * Tests the creation of an event task from user input.
     * <p>
     * The input string is in the incorrect format and an exception is thrown.
     */
    @Test
    public void eventToString_incorrectFormat_invalidTaskFormatExceptionThrown() {
        String eventStartDate = "2024-01-01";
        String eventDescription = "Event Test";
        // Missing end date
        String inputStringMissingEndDate = "event " + eventDescription + " /from " + eventStartDate;
        // Missing description
        String inputStringMissingDescription = "event /from " + eventStartDate + " /to " + eventStartDate;
        // Missing start date
        String inputStringMissingStartDate = "event " + eventDescription + " /to " + eventStartDate;
        String inputStringMissingPrefix = eventDescription + " /from " + eventStartDate + " /to " + eventStartDate;
        assertThrows(InvalidTaskFormatException.class, () -> {
            Event.createFromInput(inputStringMissingEndDate);
        });
        assertThrows(InvalidTaskFormatException.class, () -> {
            Event.createFromInput(inputStringMissingDescription);
        });
        assertThrows(InvalidTaskFormatException.class, () -> {
            Event.createFromInput(inputStringMissingStartDate);
        });
        assertThrows(InvalidTaskFormatException.class, () -> {
            Event.createFromInput(inputStringMissingPrefix);
        });
    }

    /**
     * Tests the creation of an event task from user input.
     * <p>
     * The input string is in the correct format but the date is in an incorrect
     * format and an exception is thrown.
     */
    @Test
    public void eventToString_incorrectDateFormat_invalidDateExceptionThrown() {
        String eventStartDate = "20240101";
        String eventEndDate = "20240102";
        String eventDescription = "Event Test";
        String inputString = "event " + eventDescription + " /from " + eventStartDate + " /to " + eventEndDate;
        assertThrows(InvalidDateException.class, () -> {
            Event.createFromInput(inputString);
        });
    }
}
