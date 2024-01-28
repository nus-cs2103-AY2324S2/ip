package duke.utils;

import duke.command.CommandException;
import duke.command.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_unknownCommand_exceptionThrown() {
        try {
            Parser.parseInput("some command");
        } catch (Exception e) {
            assertInstanceOf(UnknownCommandException.class, e);
        }
    }

    @Test
    public void parseInput_bye_success() {
        assertDoesNotThrow(() -> Parser.parseInput("bye"));
    }

    @Test
    public void parseInput_byeWithParameters_exceptionThrown() {
        try {
            Parser.parseInput("bye 123");
        } catch (Exception e) {
            assertInstanceOf(UnknownCommandException.class, e);
        }
    }

    @Test
    public void parseInput_list_success() {
        assertDoesNotThrow(() -> Parser.parseInput("list"));
    }

    @Test
    public void parseInput_listWithParameters_exceptionThrown() {
        try {
            Parser.parseInput("list 456");
        } catch (Exception e) {
            assertInstanceOf(UnknownCommandException.class, e);
        }
    }

    @Test
    public void parseInput_todo_success() {
        assertDoesNotThrow(() -> Parser.parseInput("todo some task"));
    }

    @Test
    public void parseInput_todoEmpty_exceptionThrown() {
        try {
            Parser.parseInput("todo");
        } catch (Exception e) {
            assertEquals("Error. Unable to create ToDo task.\nFormat: " + ToDo.CREATE_TODO_FORMAT,
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_deadline_success() {
        assertDoesNotThrow(() -> Parser.parseInput("deadline some task /by 11-11-1111 11:11"));

    }

    @Test
    public void parseInput_deadlineWrongFormats_exceptionThrown() {
        String commandExceptionMsg = "Error. Unable to create task.\nFormat: " + Deadline.CREATE_DEADLINE_FORMAT;

        // Empty parameters
        try {
            Parser.parseInput("deadline");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }

        // Missing tag
        try {
            Parser.parseInput("deadline some task 11-11-1111 11:11");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseInput("deadline some task /by 11-11-1111 1111");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }
    }

    @Test
    public void parseInput_event_success() {
        assertDoesNotThrow(() -> Parser.parseInput("event some task /from 11-11-1111 11:11 /to 12-12-1212 12:12"));
    }

    @Test
    public void parseInput_eventWrongFormats_exceptionThrown() {
        String commandExceptionMsg = "Error. Unable to create task.\nFormat: " + Event.CREATE_EVENT_FORMAT;

        // Empty parameters
        try {
            Parser.parseInput("event");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }

        // Missing tag
        try {
            Parser.parseInput("event some task 11-11-1111 11:11 /to 12-12-1212 12:12");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseInput("event some task /from 11/11/1111 11:11 /to 12-12-1212 12:12");
        } catch (Exception e) {
            assertEquals(commandExceptionMsg, e.getMessage());
        }
    }

    @Test
    public void parseInput_markWithNumber_success() {
        assertDoesNotThrow(() -> Parser.parseInput("mark 1"));
    }

    @Test
    public void parseInput_markWithNonNumbers_exceptionThrown() {
        try {
            Parser.parseInput("mark test");
        } catch (Exception e) {
            assertEquals("Error. Mark expects the index of task to be marked.", e.getMessage());
        }
    }

    @Test
    public void parseInput_unmarkWithNumber_success() {
        assertDoesNotThrow(() -> Parser.parseInput("unmark 20"));
    }

    @Test
    public void parseInput_unmarkWithNonNumbers_exceptionThrown() {
        try {
            Parser.parseInput("unmark test");
        } catch (Exception e) {
            assertEquals("Error. Unmark expects the index of task to be unmarked.", e.getMessage());
        }
    }

    @Test
    public void parseInput_deleteWithNumber_success() {
        assertDoesNotThrow(() -> Parser.parseInput("delete 100"));
    }

    @Test
    public void parseInput_deleteWithNonNumbers_exceptionThrown() {
        try {
            Parser.parseInput("delete test");
        } catch (Exception e) {
            assertEquals("Error. Delete expects the index of task to be deleted.", e.getMessage());
        }
    }

    @Test
    public void parseInput_find_success() {
        assertDoesNotThrow(() -> Parser.parseInput("find some value"));
        // Allow finding of space character.
        assertDoesNotThrow(() -> Parser.parseInput("find  "));
    }

    @Test
    public void parseInput_findSingularSpace_exceptionThrown() {
        try {
            Parser.parseInput("find ");
        } catch (Exception e) {
            assertEquals("Error. Parameter for find Command cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseData_todoCorrectFormat_success() {
        try {
            assertInstanceOf(ToDo.class, Parser.parseData("T | 1 | some task"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseData_todoWrongFormat_exceptionThrown() {
        try {
            Parser.parseData("T | 1 |");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }

    @Test
    public void parseData_deadlineCorrectFormat_success() {
        try {
            assertInstanceOf(Deadline.class, Parser.parseData("D | 0 | some task /by 11-11-1111 11:11"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseData_deadlineWrongFormat_exceptionThrown() {
        // Missing Tag
        try {
            Parser.parseData("D | 1 | someTask /b 11-11-1111 11:11");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseData("D | 0 | someTask /by 11-11/1111 11.11");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }

    @Test
    public void parseData_eventCorrectFormat_success() {
        try {
            assertInstanceOf(Event.class, Parser.parseData(
                    "E | 1 | some task /from 11-11-1111 11:11 /to 12-12-1212 12:12"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseData_eventWrongFormat_exceptionThrown() {
        // Missing Tag
        try {
            Parser.parseData("E | 0 | someTask /from 11-11-1111 11:11 12-12-1212 12:12");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseData("E | 0 | someTask /from 11-11-1111 11:11 /to 12-12-1212 12|12");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }

    @Test
    public void parseData_unknownFormat_exceptionThrown() {
        try {
            Parser.parseData("U | 1 | Some random tasks");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }
}
