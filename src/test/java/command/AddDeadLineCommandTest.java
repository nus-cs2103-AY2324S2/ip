package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;



public class AddDeadLineCommandTest {

    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddDeadlineCommand("deadline ")
                    .executeCommand(new TaskList(), new Ui(), new Storage("duke"));
        });
        assertEquals("Missing the description!", exception.getMessage());
    }


    @Test
    public void executeCommand_missingDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddDeadlineCommand("deadline borrow book")
                    .executeCommand(new TaskList(), new Ui(), new Storage("duke"));
        });
        assertEquals("Invalid format for new Deadline!", exception.getMessage());
    }

}
