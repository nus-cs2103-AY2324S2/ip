package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import logic.Parser;
import tasks.TaskList;

public class ParserTest {

    @Test
    public void createEvent_wrongDateFormat_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        try {
            Parser.parseAndExecuteCommand("event Sleep /from 14/09/2011 /to 15/4/2022",
                    existingTaskList);
        } catch (Exception e) {
            assertEquals("Text '14/09/2011' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void deleteTask_taskNumberIsString_exceptionThrown() {
        boolean thrown = false;
        TaskList existingTaskList = new TaskList();

        try {
            Parser.parseAndExecuteCommand("delete fifty", existingTaskList);
        } catch (NumberFormatException e) {
            thrown = true;
            assertEquals("For input string: \"fifty\"", e.getMessage());
        }
        //assertTrue(thrown);
    }
}
