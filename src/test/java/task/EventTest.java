package task; //same package as the class being tested

import org.junit.jupiter.api.Test;

import common.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventCreatedSuccessfully() {
        try {
            new Event("taskName", "12/10/2010-1900", "12/10/2010-2000");
        } catch (Exception e) {
            System.out.println("Deadline not created");
        }
    }

    @Test
    public void testWrongStartDataTimeFormat() {
        try {
            new Event("taskName", "20-2-2022, 1200", "12/10/2022-2200");
        } catch (DukeException e) {
            String errorMessage = ("Start time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy-HHmm\n"
                    + "Received: " + "20-2-2022, 1200" + "\n"
                    + "\"" + "taskName" + "\" not added to the list.");

            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void testWrongEndDataTimeFormat() {
        try {
            new Event("taskName", "20/2/2022-1200", "12/10/2022");
        } catch (DukeException e) {
            String errorMessage = ("End time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy-HHmm\n"
                    + "Received: " + "12/10/2022" + "\n"
                    + "\"" + "taskName" + "\" not added to the list.");

            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void testToStringSuccessful() {
        try {
            Event e = new Event("taskName", "10/10/2012-1900", "12/10/2022-2200");
            assertEquals("[E][ ] taskName (from: Oct 10 2012, 19:00 to: Oct 12 2022, 22:00)", e.toString());
        } catch (DukeException e) {
        }
    }

    @Test
    public void testToDataSuccessful() {
        try {
            Event e = new Event("taskName", "10/10/2012-1900", "12/10/2022-2200");
            assertEquals("E | 0 | taskName | Oct 10 2012, 19:00 - Oct 12 2022, 22:00", e.toData());
        } catch (DukeException e) {
        }

    }
}
