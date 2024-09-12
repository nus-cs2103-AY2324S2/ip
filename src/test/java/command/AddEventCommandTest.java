package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;


public class AddEventCommandTest {


    /**
     * Tests the creation of Event task without description.
     */
    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event ")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Missing the description!", exception.getMessage());
    }


    /**
     * Tests the creation of Event task without from and to attributes.
     */
    @Test
    public void executeCommand_missingTwoDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event meeting")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Invalid format for new Event!"
                + "\nIt should be 'event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'."
                + "\nYou can add a priority level to this task by adding this '/priority'-- High, Medium and Low.", exception.getMessage());
    }

    /**
     * Tests the creation of Event task without to attribute.
     */
    @Test
    public void executeCommand_missingOneDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddEventCommand("event meeting /from 2024-10-01 23:59")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Invalid format for new Event!"
                + "\nIt should be 'event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'."
                + "\nYou can add a priority level to this task by adding this '/priority'-- High, Medium and Low.",
                exception.getMessage());
    }
}
