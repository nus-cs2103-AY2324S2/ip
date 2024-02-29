package task; //same package as the class being tested

import org.junit.jupiter.api.Test;

import common.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineCreatedSuccessfully() {
        try {
            new Deadline("taskName", "12/10/2010-1900");
        } catch (Exception e) {
            System.out.println("Deadline not created");
        }
    }

    @Test
    public void testWrongDataTimeFormat() {
        try {
            new Deadline("taskName", "20-2-2022, 1200");
        } catch (DukeException e) {
            String errorMessage = ("Date and Time not in the correct format.\n"
                    + "Correct format: dd/MM/yyyy-HHmm\n"
                    + "Received: " + "20-2-2022, 1200" + "\n"
                    + "\"" + "taskName" + "\" not added to the list.");

            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void testToStringSuccessful() {
        try {
            Deadline d = new Deadline("taskName", "10/10/2012-1900");
            assertEquals("[D][ ] taskName (by: Oct 10 2012, 19:00)", d.toString());
        } catch (DukeException e) {
        }
    }

    @Test
    public void testToDataSuccessful() {
        try {
            Deadline d = new Deadline("taskName", "10/10/2012-1900");
            assertEquals("D | 0 | taskName | Oct 10 2012, 19:00", d.toData());
        } catch (DukeException e) {
        }

    }
}
