package duke.commands;

import duke.exception.MalformedUserInputException;
import org.junit.jupiter.api.Test;

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

    @Test
    public void addCommand_successful() {
        final String[] validDates = {"1992-01-01", "2013-01-09", "2023-01-09", "2024-01-09", "2024-01-03"};
        final String[] activityNames = {"drill through the wall", "cook laksa", "write test case", "swim", "dance in the pool"};

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
