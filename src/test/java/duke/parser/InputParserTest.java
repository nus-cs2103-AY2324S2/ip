package duke.parser;

import duke.TaskList;
import duke.TextTemplate;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidMarkException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    @Test
    public void processCommandBye() throws InvalidInputException, InvalidMarkException{
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        assertEquals(ip.processCommand("bye", tasks), TextTemplate.EXIT + "\n" + TextTemplate.LINE_BREAK);
        assertFalse(ip.isActive());
    }

    @Test
    public void processCommandDeadline() throws InvalidInputException, InvalidMarkException {
        InputParser ip = new InputParser();
        TaskList tasks = new TaskList();
        String actualResponse = ip.processCommand("deadline activity 1 /by 2024-12-01", tasks);
        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, tasks.size());
        String expectedResponse = TextTemplate.ADD_TASK + "\n" + "[D][ ] activity 1 (by: Dec 01 2024 00:00)" + "\n" +
                taskCounterMsg + "\n" + TextTemplate.LINE_BREAK;
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
        } catch (InvalidMarkException e) {
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
        } catch (InvalidMarkException e) {
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
        } catch (InvalidInputException e) {
            assertEquals(TextTemplate.INVALID_DATETIME, e.getMessage());
        } catch (InvalidMarkException e) {
            e.printStackTrace();
            fail();
        }
    }

}
