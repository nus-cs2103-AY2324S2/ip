package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;



public class AddDeadLineCommandTest {

    /**
     * Tests the creation of deadline task without description.
     */
    @Test
    public void executeCommand_missingDescription_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddDeadlineCommand("deadline ")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Missing the description!", exception.getMessage());
    }


    /**
     * Tests the creation of deadline task without by attribute.
     */
    @Test
    public void executeCommand_missingDateTime_exceptionThrown() {
        AndeluException exception = assertThrows(AndeluException.class, ()
                -> {
            new AddDeadlineCommand("deadline borrow book")
                    .executeCommand(new TaskList(), new Ui(), new Storage("andeluFile"));
        });
        assertEquals("Invalid format for new Deadline!"
                + "\nIt should be 'deadline DESCRIPTION /by yyyy-MM-dd HH:mm'."
                + "\nYou can add a priority level to this task by adding this '/priority'-- High, Medium and Low.",
                exception.getMessage());
    }

}
