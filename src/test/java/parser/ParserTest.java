package parser;

import org.junit.jupiter.api.Test;
import tasks.TaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void createEvent_wrongDateFormat_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        try {
            Parser.processCommand("event Sleep /from 14/09/2011 /to 15/4/2022",
                    existingTaskList);
        }
        catch (Exception e) {
            assertEquals("Text '14/09/2011' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void deleteTask_taskNumberIsString_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        try {
            Parser.processCommand("delete fifty", existingTaskList);
        }
        catch (NumberFormatException e) {
            assertEquals("For input string: \"fifty\"", e.getMessage());
        }
    }
}
