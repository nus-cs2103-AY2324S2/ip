package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.EditCommand;
import exception.BuddyException;



public class EditParserTest {
    @Test
    public void parseEdit_normalInput_success() {
        EditParser ep = new EditParser(0, "/task new task /by 20/09/2001 0000");
        try {
            boolean isCorrectCommand = ep.parseEdit() instanceof EditCommand;
            assertEquals(isCorrectCommand, true);
        } catch (BuddyException e) {
            fail();
        }
    }

    @Test
    public void parseEdit_emptyTask_exceptionThrown() {
        EditParser ep = new EditParser(0, "/task /by 20/09/2001 0000");
        try {
            ep.parseEdit();
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Task cannot be empty!");
        }
    }

    @Test
    public void parseEdit_incorrectDateFormat_exceptionThrown() {
        EditParser ep = new EditParser(0, "/task new task /to 99/99/2001 2000");
        try {
            ep.parseEdit();
            fail();
        } catch (BuddyException e) {
            assertEquals(e.getMessage(), "Not a valid date format!");
        }
    }
}
