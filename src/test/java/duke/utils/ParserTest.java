package duke.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

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
            assertEquals("Error. Unable to create ToDo task.\nFormat: " + ToDo.INPUT_TODO_FORMAT,
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_deadline_success() {
        assertDoesNotThrow(() -> Parser.parseInput("deadline some task /by 11-11-1111 11:11"));

    }

    @Test
    public void parseInput_deadlineWrongFormats_exceptionThrown() {
        String commandExceptionMsg = "Error. Unable to create task.\nFormat: " + Deadline.INPUT_DEADLINE_FORMAT;

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
        String commandExceptionMsg = "Error. Unable to create task.\nFormat: " + Event.INPUT_EVENT_FORMAT;

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
    public void parseInput_priority_success() {
        assertDoesNotThrow(() -> Parser.parseInput("priority 100 high"));
        // Empty should be recognized as none
        assertDoesNotThrow(() -> Parser.parseInput("priority 1"));
    }

    @Test
    public void parseInput_priorityWithInvalidPriority_exceptionThrown() {
        try {
            Parser.parseInput("priority 1 kekw");
        } catch (Exception e) {
            assertEquals("Error. Unknown priority value. Only high/low/none are allowed.", e.getMessage());
        }
    }

    @Test
    public void parseInput_priorityWithNonNumbers_exceptionThrown() {
        try {
            Parser.parseInput("priority one low");
        } catch (Exception e) {
            assertEquals("Error. Priority expects the index of task to be modified.", e.getMessage());
        }
    }

    @Test
    public void parseData_todoCorrectFormat_success() {
        try {
            assertInstanceOf(ToDo.class, Parser.parseData("T | 1 | some task | none"));
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
            assertInstanceOf(Deadline.class, Parser.parseData("D | 0 | some task /by 11-11-1111 11:11 | low"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseData_deadlineWrongFormat_exceptionThrown() {
        // Missing Tag
        try {
            Parser.parseData("D | 1 | someTask /b 11-11-1111 11:11 | low");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseData("D | 0 | someTask /by 11-11/1111 11.11 | none");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong priority format
        try {
            Parser.parseData("D | 1 | someTask /by 11-11-1111 11:11 | nonexistent");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }

    @Test
    public void parseData_eventCorrectFormat_success() {
        try {
            assertInstanceOf(Event.class, Parser.parseData(
                    "E | 1 | some task /from 11-11-1111 11:11 /to 12-12-1212 12:12 | high"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseData_eventWrongFormat_exceptionThrown() {
        // Missing Tag
        try {
            Parser.parseData("E | 0 | someTask /from 11-11-1111 11:11 12-12-1212 12:12 | low");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong datetime format
        try {
            Parser.parseData("E | 0 | someTask /from 11-11-1111 11:11 /to 12-12-1212 12|12 | none");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }

        // Wrong priority format
        try {
            Parser.parseData("E | 0 | someTask /from 11-11-1111 11:11 /to 12-12-1212 12:12");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }

    @Test
    public void parseData_unknownFormat_exceptionThrown() {
        try {
            Parser.parseData("U | 1 | Some random tasks | none");
        } catch (Exception e) {
            assertEquals("Task cannot be loaded due to incorrect format. Skipping.", e.getMessage());
        }
    }
}
