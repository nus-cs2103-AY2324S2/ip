package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.TextTemplate;
import duke.exceptions.InvalidDateFormException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidMarkException;

public class InputParserTest {

    @Test
    public void processCommandDeadline() throws InvalidInputException, InvalidMarkException, InvalidDateFormException {
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        String actualResponse = ip.processCommand("deadline activity 1 /by 2024-12-01", tasks);
        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        String expectedResponse = "[D][ ] activity 1 (by: Dec 01 2024 00:00)" + "\n"
                + taskCounterMsg;
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void processCommandEmptyTodoDesc() {
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        try {
            ip.processCommand("todo", tasks);
            fail("Expected InvalidInputException");
        } catch (InvalidInputException e) {
            assertEquals(TextTemplate.TODO_NO_DESC, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void processCommandInvalidCommand() {
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        try {
            ip.processCommand("zzz", tasks);
            fail("Expected InvalidInputException");
        } catch (InvalidInputException e) {
            assertEquals(TextTemplate.INVALID_COMMAND, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void processCommandInvalidDateTime() {
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        try {
            ip.processCommand("deadline activity 1 /by 1600", tasks);
            fail("Expected InvalidInputException");
        } catch (InvalidDateFormException e) {
            assertEquals(TextTemplate.INVALID_DATETIME, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
