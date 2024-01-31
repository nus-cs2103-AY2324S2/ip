package duke.commands;

import duke.exception.MalformedUserInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventCommandTest {

    public static String whitespace = " ";

    @Test
    public void addCommand_emptyEvent_throwsException() {
        assertConstructingInvalidEventCmdThrowsException("some event name", whitespace, "2024-01-01");
        assertConstructingInvalidEventCmdThrowsException("some event name", "2024-01-01", whitespace);
        assertConstructingInvalidEventCmdThrowsException(whitespace, "2024-01-01", "2024-01-01");
    }


    @Test
    public void addCommand_invalidEventName_throwsException() {
        final String[] invalidNames = {"", " ", "               "};

        for (String name : invalidNames) {
            assertConstructingInvalidEventCmdThrowsException(name, "2024-01-01", "2024-02-01");
        }
    }


    @Test
    public void addCommand_invalidDate_throwsException() {
        final String[] invalidDates = {"12 Jan 2023", "2024-31-01", "2024-0100-21", "24-12-12"};

        for (String startDate : invalidDates) {
            for (String endDate : invalidDates) {
                assertConstructingInvalidEventCmdThrowsException("some event name", startDate, endDate);
            }
        }
    }


    private void assertConstructingInvalidEventCmdThrowsException(String name, String startTime, String endTime) {
        try {
            new EventCommand(name, startTime, endTime);
        } catch (MalformedUserInputException e) {
            return;
        }

        String error = String.format(
                "An event command was successfully constructed with invalid input: %s %s %s",
                name, startTime, endTime);

        fail(error);
    }

    /**
     * Asserts that attempting to construct an add command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidEventCmdDoesNotThrowsException(String name, String startTime, String endTime) {
        try {
            new EventCommand(name, startTime, endTime);
        } catch (MalformedUserInputException e) {
            String error = String.format(
                    "An event command was successfully constructed with invalid input: %s %s %s",
                    name, startTime, endTime);

            fail(error);
        }

    }
}
