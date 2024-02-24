package jerome.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jerome.exception.MalformedUserInputException;

/**
 * This class is a JUnit test class for the EventCommand class.
 * Tests the functionality of the {@code EventCommand} class.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class EventCommandTest {

    /**
     * Represents a variable for whitespace to test for empty event name
     * or date.
     */
    public static final String WHITESPACE = " ";


    /**
     * Tests adding an empty event, start date or end date with a whitespace as name.
     */
    @Test
    public void addCommand_emptyEvent_throwsException() {
        assertConstructingInvalidEventCmdThrowsException("some event name", WHITESPACE, "2024-01-01");
        assertConstructingInvalidEventCmdThrowsException("some event name", "2024-01-01", WHITESPACE);
        assertConstructingInvalidEventCmdThrowsException(WHITESPACE, "2024-01-01", "2024-01-01");
    }


    /**
     * Tests different kinds of invalid event name.
     */
    @Test
    public void addCommand_invalidEventName_throwsException() {
        final String[] invalidNames = {"", " ", "               "};

        for (String name : invalidNames) {
            assertConstructingInvalidEventCmdThrowsException(name, "2024-01-01", "2024-02-01");
        }
    }


    /**
     * Tests different kinds of invalid dates.
     */
    @Test
    public void addCommand_invalidDate_throwsException() {
        final String[] invalidDates = {"12 Jan 2023", "2024-31-01", "2024-0100-21", "24-12-12"};

        for (String startDate : invalidDates) {
            for (String endDate : invalidDates) {
                assertConstructingInvalidEventCmdThrowsException("some event name", startDate, endDate);
            }
        }
    }

    /**
     * Tests a successful add command.
     */
    @Test
    public void addCommand_successful() {
        final String[] validDates = {"1992-01-01", "2013-01-09", "2023-01-09", "2024-01-09", "2024-01-03"};
        final String[] activityNames = {"drill the wall", "cook laksa", "write test case", "swim", "dance in pool"};

        for (String startDate : validDates) {
            for (String endDate : validDates) {
                for (String activity : activityNames) {
                    assertConstructingValidEvent(activity, startDate, endDate);
                }
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

    private void assertConstructingValidEvent(String name, String startTime, String endTime) {
        try {
            new EventCommand(name, startTime, endTime);
            return;
        } catch (MalformedUserInputException e) {
            String error = String.format(
                    "An event command was successfully constructed with invalid input: %s %s %s",
                    name, startTime, endTime);

            fail(error);
        }
    }

}
