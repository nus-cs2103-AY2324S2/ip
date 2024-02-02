package ukecat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static ukecat.Parser.parseDeleteTask;
import static ukecat.Parser.parseTaskToCsv;
import java.time.LocalDate;

public class ParserTest {
    @Test
    public void parseDeleteTask_validInput_success() throws UkeCatException {
        // returns 1, the index of task to delete in list
        assertEquals(1, parseDeleteTask(new String[]{"delete", "2"}));

    }

    @Test
    public void parseDeleteTask_invalidInput_exceptionThrown() {
        try {
            assertEquals(0, parseDeleteTask(new String[]{"mark", "2", "now"}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: mark / unmark <task#>", e.getMessage());
        }
        try {
            assertEquals(0, parseDeleteTask(new String[]{"mark", "me"}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: mark / unmark <task#>", e.getMessage());
        }
        try {
            assertEquals(0, parseDeleteTask(new String[]{"mark"}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: mark / unmark <task#>", e.getMessage());
        }
    }

    @Test
    public void parseTaskToCsv_validInput_success() {
        // returns string representation of tasks for csv
        assertEquals("T,0,watch lecture",
                parseTaskToCsv(new ToDo("0", "watch lecture")));

        assertEquals("D,1,feed cat,2024-02-10",
                parseTaskToCsv(new Deadline("1", "feed cat",
                        LocalDate.parse("2024-02-10"))));

        // lack of isDone -> isDone is automatically 0
        assertEquals("E,0,holiday,2024-02-11,2024-02-11",
                parseTaskToCsv(new Event("holiday", LocalDate.parse("2024-02-11"),
                        LocalDate.parse("2024-02-11"))));
    }

}