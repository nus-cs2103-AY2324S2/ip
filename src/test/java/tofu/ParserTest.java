package tofu;

import tofu.command.ExitCommand;
import tofu.command.ListCommand;
import tofu.task.Deadline;
import tofu.task.Event;
import tofu.task.ToDo;
import tofu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    public void parseToTask_correctFormat_success() throws DukeException {
        // parse string to Task for ToDo
        assertEquals(new ToDo("test"), Parser.parseToTask("T |   | test", " | "));
        // parse string to Task for Deadline
        assertEquals(new Deadline("test", "2024-01-01"),
                Parser.parseToTask("D |   | test | 2024-01-01", " | "));
        // parse string to Task for Event
        assertEquals(new Event("test", "2024-01-01", "2024-02-02"),
                Parser.parseToTask("E |   | test | 2024-01-01 | 2024-02-02", " | "));
    }

    public void parseToTask_wrongFormat_DukeExceptionThrown() {
        try {
            assertEquals(new ToDo("test"), Parser.parseToTask("O |   | test", " | "));
            fail();
        } catch (DukeException ex) {
            assertEquals("Corrupted Data!", ex.getMessage());
        }
    }

    public void parseToCommand_correctFormat_success() throws DukeException {
        // parse command for List
        assertEquals(new ListCommand(), Parser.parseToCommand("list"));
        // parse command for bye
        assertEquals(new ExitCommand(), Parser.parseToCommand("bye"));
    }

    public void parseToCommand_wrongFormat_DukeExceptionThrown() {
        try {
            assertEquals(0, Parser.parseToCommand("test"));
            fail();
        } catch (DukeException ex) {
            assertEquals(Ui.unknownCommandError(), ex.getMessage());
        }
    }
}
