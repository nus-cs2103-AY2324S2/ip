package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void parseTodoCommand_validCommand_success() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String outputMessage = parser.parse("todo read book");
        assertEquals(ui.getToDoMessage(new Todo("read book"), 1), outputMessage);
        assertEquals(1, taskList.getSize());
        outputMessage = parser.parse("todo read manga");
        assertEquals(ui.getToDoMessage(new Todo("read manga"), 2), outputMessage);
        assertEquals(2, taskList.getSize());
    }

    @Test
    void parseTodoCommand_emptyDescription_failure() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        // no description
        try {
            parser.parse("todo");
            fail();
        } catch (TodoException e) {
            assertEquals("Bruh... No description given!\nPlease follow this format:"
                    + "\ntodo {TODO_DESCRIPTION}", e.getMessage());
            assertEquals(0, taskList.getSize());
        }
        // one empty white space
        try {
            parser.parse("todo ");
            fail();
        } catch (TodoException e) {
            assertEquals("Bruh... No description given!\nPlease follow this format:"
                    + "\ntodo {TODO_DESCRIPTION}", e.getMessage());
            assertEquals(0, taskList.getSize());
        }
        // two empty white space
        try {
            parser.parse("todo  ");
            fail();
        } catch (TodoException e) {
            assertEquals("Bruh... No description given!\nPlease follow this format:"
                    + "\ntodo {TODO_DESCRIPTION}", e.getMessage());
            assertEquals(0, taskList.getSize());
        }
    }
    @Test
    void parseDeadlineCommand_validDeadlineCommand_success() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        String outputMessage = parser.parse("deadline cs2103 quiz 3 /by 23 03 2024 2359");
        assertEquals(ui.getDeadlineMessage(
                new Deadline("cs2103 quiz 3", "23 03 2024 2359"), 1), outputMessage);
        assertEquals(1, taskList.getSize());
        outputMessage = parser.parse("deadline cs2107 assignment /by 28 02 2024 2359");
        assertEquals(ui.getDeadlineMessage(
                new Deadline("cs2107 assignment", "28 02 2024 2359"), 2), outputMessage);
        assertEquals(2, taskList.getSize());
    }

    @Test
    void parseDeadlineCommand_emptyDescriptionAndInvalidDateCommand_failure() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList, ui, storage);
        // no description
        try {
            parser.parse("deadline");
            fail();
        } catch (DeadlineException e) {
            assertEquals("Bruh... No description given!\nPlease follow the format:\ndeadline "
                    + "{DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}", e.getMessage());
            assertEquals(taskList.getSize(), 0);
        }
        // incorrect date format
        try {
            parser.parse("deadline cs2103 quiz /by 23022024 3pm");
            fail();
        } catch (DeadlineException e) {
            assertEquals(e.getMessage(), "Bruh... Invalid date format!\nPlease follow the format:\ndeadline "
                    + "{DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}");
            assertEquals(taskList.getSize(), 0);
        }
    }
}
