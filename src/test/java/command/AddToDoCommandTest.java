package command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;


public class AddToDoCommandTest {

    /**
     * Tests the creation of ToDo task with no description.
     */
    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddToDoCommand("todo ")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Missing the description!", exception.getMessage());
    }
}
