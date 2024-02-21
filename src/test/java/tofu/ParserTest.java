package tofu;

import org.junit.jupiter.api.Test;
import tofu.command.*;
import tofu.task.Deadline;
import tofu.task.Event;
import tofu.task.ToDo;
import tofu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseToTask_correctFormat_success() throws TofuException {
        // parse string to Task for ToDo
        assertEquals(new ToDo("test"), Parser.parseToTask("T |   | test", "\\|"));
        // parse string to Task for Deadline
        assertEquals(new Deadline("test", "2024-01-01"),
                Parser.parseToTask("D |   | test | 2024-01-01", "\\|"));
        // parse string to Task for Event
        assertEquals(new Event("test", "2024-01-01", "2024-02-02"),
                Parser.parseToTask("E |   | test | 2024-01-01 | 2024-02-02", "\\|"));
    }

    @Test
    public void parseToTask_wrongFormat_DukeExceptionThrown() {
        try {
            assertEquals(new ToDo("test"), Parser.parseToTask("O |   | test", "\\|"));
            fail();
        } catch (TofuException ex) {
            assertEquals("Corrupted Data!", ex.getMessage());
        }
    }

    @Test
    public void parseToCommand_correctFormat_success() throws TofuException {
        // parse command for List
        assertEquals(new ListCommand(), Parser.parseToCommand("list"));
        // parse command for Add, todo
        assertEquals(new AddCommand("todo", "sleep"), Parser.parseToCommand("todo sleep"));
        // parse command for Add, deadline
        assertEquals(new AddCommand("deadline", "assignment /by 2024-01-01"),
                Parser.parseToCommand("deadline assignment /by 2024-01-01"));
        // parse command for Add, event
        assertEquals(new AddCommand("event", "recess week /from 2024-01-01 /to 2024-02-02"),
                Parser.parseToCommand("event recess week /from 2024-01-01 /to 2024-02-02"));
        // parse command for Delete
        assertEquals(new DeleteCommand(2), Parser.parseToCommand("delete 3"));
        // parse command for Mark
        assertEquals(new MarkCommand(2, true), Parser.parseToCommand("mark 3"));
        // parse command for Unmark
        assertEquals(new MarkCommand(2, false), Parser.parseToCommand("unmark 3"));
        // parse command for Find
        assertEquals(new FindCommand("test"), Parser.parseToCommand("find test"));

    }

    @Test
    public void parseToCommand_wrongFormat_DukeExceptionThrown() {
        try {
            assertEquals(0, Parser.parseToCommand("test"));
            fail();
        } catch (TofuException ex) {
            assertEquals(Ui.unknownCommandError(), ex.getMessage());
        }
    }
}
