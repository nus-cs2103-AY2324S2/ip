package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fredricksen.exceptions.FredricksenException;
import fredricksen.tasks.Task;

public class FormatDateTest {
    private Task task = new Task("", "", false);
    @Test
    public void dateWithTime_correctFormat_success() {
        try {
            assertEquals("Feb 12 2019, 07:00 PM", task.formatDates("12/2/2019 1900"));
        } catch (FredricksenException err) {
            System.out.println(err.getMessage());
        }
    }

    @Test
    public void dateWoTime_correctFormat_success() {
        try {
            assertEquals("Feb 12 2019", task.formatDates("12/2/2019"));
        } catch (FredricksenException err) {
            System.out.println(err.getMessage());
        }
    }

    @Test
    public void dateWoTime_incorrectDateFormatHandled_exceptionThrown() {
        try {
            assertEquals(task.formatDates("200/200/2019"), "");
        } catch (FredricksenException err) {
            assertEquals(null, err.getMessage());
        }
    }

    @Test
    public void dateWithTime_incorrectTimeFormatHandled_exceptionThrown() {
        try {
            assertEquals(task.formatDates("2/2/2019 231"), "");
        } catch (FredricksenException err) {
            assertEquals(null, err.getMessage());
        }
    }
}
