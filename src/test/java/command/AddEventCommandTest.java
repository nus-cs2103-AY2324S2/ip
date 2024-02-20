package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;


public class AddEventCommandTest {



    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event ")
                    .executeCommand(new TaskList(), new Ui(), new Storage("duke"));
        });
        assertEquals("Missing the description!", exception.getMessage());
    }


    @Test
    public void executeCommand_missingTwoDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event meeting")
                    .executeCommand(new TaskList(), new Ui(), new Storage("duke"));
        });
        assertEquals("Invalid format for new Event!", exception.getMessage());
    }

    @Test
    public void executeCommand_missingOneDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event meeting /from 2024-10-01 23:59")
                    .executeCommand(new TaskList(), new Ui(), new Storage("duke"));
        });
        assertEquals("Invalid format for new Event!", exception.getMessage());
    }
}
