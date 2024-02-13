package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.CommandResponse;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Tests for the Event class.
 */
public class EventTest {

    private Parser parser;

    public EventTest() {
        try {
            TaskList tasklist = new TaskList();
            Storage storage = new Storage("TestGeePeeTee.txt");
            Ui ui = new Ui();
            parser = new Parser(tasklist, storage, ui);
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
    public void eventToString_incorrectFormat_errorThrown() {
        String eventStartDate = "2024-01-01";
        String eventDescription = "Event Test";

        // Missing end date
        String inputStringMissingEndDate = "event " + eventDescription + " /from " + eventStartDate;
        CommandResponse responseMissingEndDate = parser.parseInput(inputStringMissingEndDate);
        Boolean isError = responseMissingEndDate.isError();
        assertEquals(true, isError);

        // Missing description
        String inputStringMissingDescription = "event /from " + eventStartDate + " /to " + eventStartDate;
        CommandResponse responseMissingDescription = parser.parseInput(inputStringMissingDescription);
        isError = responseMissingDescription.isError();
        assertEquals(true, isError);

        // Missing start date
        String inputStringMissingStartDate = "event " + eventDescription + " /to " + eventStartDate;
        CommandResponse responseMissingStartDate = parser.parseInput(inputStringMissingStartDate);
        isError = responseMissingStartDate.isError();
        assertEquals(true, isError);

        // Missing prefix
        String inputStringMissingPrefix = eventDescription + " /from " + eventStartDate + " /to " + eventStartDate;
        CommandResponse responseMissingPrefix = parser.parseInput(inputStringMissingPrefix);
        isError = responseMissingPrefix.isError();
        assertEquals(true, isError);

    }
}
