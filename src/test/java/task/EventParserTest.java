package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import parser.EventParser;

/**
 * Tests for the EventParser class.
 */
public class EventParserTest {

    /**
     * Tests the parsing of an event task from the user input.
     */
    @Test
    public void parseEvent_correctInput() {
        String input = "event Description /from 2024-02-20 /to 2024-02-25";
        try {
            Event event = EventParser.parseEvent(input);
            assertEquals("Description", event.getDescription());
            assertEquals(LocalDate.of(2024, 2, 20), event.getStartDate());
            assertEquals(LocalDate.of(2024, 2, 25), event.getDeadlineDate());
        } catch (InvalidTaskFormatException | InvalidDateException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    /**
     * Tests the parsing of an event task from the user input with a different date
     * format.
     */
    @Test
    public void parseEvent_incorrectFormat_errorThrown() {
        String input = "event Description /from /to";
        try {
            EventParser.parseEvent(input);
            fail("Expected InvalidTaskFormatException was not thrown");
        } catch (InvalidTaskFormatException | InvalidDateException e) {
            return;
        }
    }

    /**
     * Tests the parsing of an event task from the user input with an incorrect
     * date.
     */
    @Test
    public void parseEvent_incorrectDate_errorThrown() {
        String input = "event Description /from 2022-02-20 /to 2022-02-25";
        try {
            EventParser.parseEvent(input);
            fail("Expected InvalidDateException was not thrown");
        } catch (InvalidTaskFormatException | InvalidDateException e) {
            return;
        }
    }
}
