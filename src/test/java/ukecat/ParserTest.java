package ukecat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ukecat.Parser.parseDeleteTask;
import static ukecat.Parser.parseTaskToCsv;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for the {@link ukecat.Parser} class.
 */
public class ParserTest {

    /**
     * Test case for valid input in the {@link ukecat.Parser#parseDeleteTask(String[])} method.
     *
     * @throws UkeCatException If an unexpected exception occurs.
     */
    @Test
    public void parseDeleteTask_validInput_success() throws UkeCatException {
        // returns 1, the index of task to delete in list
        assertEquals(1, parseDeleteTask(new String[]{"delete", "2"}));

    }

    /**
     * Test case for invalid input in the {@link ukecat.Parser#parseDeleteTask(String[])} method.
     */
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

    /**
     * Test case for an empty input in the {@link ukecat.Parser#parseDeleteTask(String[])} method.
     */
    @Test
    public void parseDeleteTask_emptyInput_exceptionThrown() {
        try {
            assertEquals(0, parseDeleteTask(new String[]{}));
            fail();
        } catch (UkeCatException e) {
            assertEquals("Wrong format, use: delete <task#>", e.getMessage());
        }
    }

    /**
     * Edge case test for the {@link ukecat.Parser#parseDeleteTask(String[])} method.
     *
     * @throws UkeCatException If an unexpected exception occurs.
     */
    @Test
    public void parseDeleteTask_edgeCases_success() throws UkeCatException {
        // Edge case: Task number at the minimum allowed value
        assertEquals(0, parseDeleteTask(new String[]{"delete", "1"}));

        // Edge case: Task number at the maximum allowed value
        assertEquals(Integer.MAX_VALUE - 1, parseDeleteTask(new String[]{"delete",
                String.valueOf(Integer.MAX_VALUE)}));
    }

    /**
     * Test case for valid input in the {@link ukecat.Parser#parseTaskToCsv(Task)} method.
     */
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

    /**
     * Edge case test for the {@link ukecat.Parser#parseTaskToCsv(Task)} method.
     */
    @Test
    public void parseTaskToCsv_edgeCases_success() {
        // Edge case: Task description with special characters
        assertEquals("T,NOT_DONE,special characters *&^%$#@",
                parseTaskToCsv(new ToDo(TaskStatus.NOT_DONE, "special characters *&^%$#@")));

        // Edge case: Minimum allowed date
        assertEquals("D,COMPLETE,min date,0001-01-01",
                parseTaskToCsv(new Deadline(TaskStatus.COMPLETE, "min date",
                        LocalDate.parse("0001-01-01"))));
    }

}
