package duke;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todo_getTask_success() throws DukeException{
        assertEquals("1. [T][ ] pray", new ToDo(0, "pray").getTask());
    }

    @Test
    public void deadline_getTask_invalidDate_exceptionThrown(){
        try {
            DukeException.validateDateTime("111/11/1111");
            assertEquals("fail", new Deadline(1, "fail", "111/11/1111").getTask());
        } catch (DukeException d) {
            String[] dateFormats = {
                    "M/d/yyyy[ HHmm]",
                    "yyyy-MM-dd[ HHmm]",
                    "dd-MM-yyyy[ HHmm]",
                    "d/M/yyyy[ HHmm]",
                    "M-d-yyyy[ HHmm]",
                    "d-M-yyyy[ HHmm]"
            };
            assertEquals("Invalid date and time format, use the following formats: " +
                    "\n" + Arrays.toString(dateFormats), d.getMessage());
        }

    }
}