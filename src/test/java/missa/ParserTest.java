package missa;

import missa.command.Command;
import missa.exception.IncorrectTaskTypeException;
import missa.exception.NoContentException;
import missa.exception.NoSuchTaskException;
import missa.exception.NoTimingException;
import missa.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void strToDateTime_correctFormOfInput_success() {
        try {
            Parser parser = new Parser();
            LocalDateTime time = parser.strToDateTime("2021-2-2 2");
            assertEquals(
                    LocalDateTime.of(
                            LocalDate.of(2021, 2, 2),
                            LocalTime.of(2, 0)).toString(),
                    time.toString());
        } catch (NoTimingException e) {
            fail();
        }
    }

    @Test
    public void strToDateTime_incorrectFormOfInput_exceptionThrown() {
        try {
            Parser parser = new Parser();
            LocalDateTime time = parser.strToDateTime("2021-2-2");
            fail(); // Should not reach here
        } catch (NoTimingException e) {
            assertEquals("I am sorry, can I know the timing for this task?", e.getMessage());
        }
    }

    @Test
    public void parse_emptyInput_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            Command temp = parser.parse("", tasks);
            fail(); // Should not reach this line
        } catch (NoSuchTaskException
                 | NoTimingException
                 | NoContentException
                 | IncorrectTaskTypeException e) {
            assertEquals("Sorry, can I know the task type (eg. todo, deadline, event)?", e.getMessage());
        }
    }

    @Test
    public void parse_addTodo_success() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Parser parser = new Parser();
            tasks = parser.parse("todo clean room", tasks).execute(ui);
            Task task = tasks.getATask(0);
            assertEquals("[T][ ] clean room", task.toString());
        } catch (NoSuchTaskException
                 | NoTimingException
                 | NoContentException
                 | IncorrectTaskTypeException e) {
            fail();
        }
    }
}
