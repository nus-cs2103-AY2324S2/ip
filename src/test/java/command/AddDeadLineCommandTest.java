package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddDeadLineCommandTest {

    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {new AddDeadlineCommand("deadline ").excuteCommand(new TaskList(), new Ui(), new Storage("duke"));});
        assertEquals("Missing the description!", exception.getMessage());
    }


    @Test
    public void executeCommand_missingDateTime_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {new AddDeadlineCommand("deadline borrow book").excuteCommand(new TaskList(), new Ui(), new Storage("duke"));});
        assertEquals("Invalid format for new Deadline!", exception.getMessage());
    }

}
