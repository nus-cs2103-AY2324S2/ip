package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddEventCommandTest {



    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {new AddEventCommand("event ").executeCommand(new TaskList(), new Ui(), new Storage("duke"));});
        assertEquals("Missing the description!", exception.getMessage());
    }


    @Test
    public void executeCommand_missingTwoDateTime_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {new AddEventCommand("event meeting").executeCommand(new TaskList(), new Ui(), new Storage("duke"));});
        assertEquals("Invalid format for new Event!", exception.getMessage());
    }

    @Test
    public void executeCommand_missingOneDateTime_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {new AddEventCommand("event meeting /from 2024-10-01 23:59").executeCommand(new TaskList(), new Ui(), new Storage("duke"));});
        assertEquals("Invalid format for new Event!", exception.getMessage());
    }
}
