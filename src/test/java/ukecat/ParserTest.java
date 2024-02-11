package ukecat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ukecat.Parser.parseDeleteTask;
import static ukecat.Parser.parseTaskToCsv;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
            assertEquals("Wrong format, use: delete <task#>", e.getMessage());
        }
        try {
            assertEquals(0, parseDeleteTask(new String[]{"mark", "me"}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: delete <task#>", e.getMessage());
        }
        try {
            assertEquals(0, parseDeleteTask(new String[]{"mark"}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: delete <task#>", e.getMessage());
        }
    }

    @Test
    public void parseTaskToCsv_validInput_success() {
        assertEquals("T,NOT_DONE,watch lecture",
                parseTaskToCsv(new ToDo(TaskStatus.NOT_DONE, "watch lecture")));

        assertEquals("D,COMPLETE,feed cat,2024-02-10",
                parseTaskToCsv(new Deadline(TaskStatus.COMPLETE, "feed cat",
                        LocalDate.parse("2024-02-10"))));

        assertEquals("E,NOT_DONE,holiday,2024-02-11,2024-02-11",
                parseTaskToCsv(new Event(TaskStatus.NOT_DONE, "holiday",
                        LocalDate.parse("2024-02-11"), LocalDate.parse("2024-02-11"))));
    }

}
