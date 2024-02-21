package maltese;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import maltese.action.*;
import org.junit.jupiter.api.Test;

import maltese.exception.EmptyDescriptionException;
import maltese.exception.NoIndexException;
import maltese.exception.UnknownCommandException;
import maltese.exception.WrongDateFormatException;
import maltese.exception.WrongDateOrderingException;




public class ParserTest {

    @Test
    void test_parseCommand_validCommands() {
        TaskList taskList = new TaskList();
        Action action;

        try {
            // Test "bye" command
            action = Parser.parseCommand("bye", taskList);
            assertTrue(action instanceof Farewell);

            // Test "list" command
            action = Parser.parseCommand("list", taskList);
            assertTrue(action instanceof TaskList);


            // Test "todo" command
            action = Parser.parseCommand("todo Task description", taskList);
            assertTrue(action instanceof Echo);

            // Test "delete" command
            action = Parser.parseCommand("delete 1", taskList);
            assertTrue(action instanceof Delete);

            // Test "deadline" command
            action = Parser.parseCommand("deadline Task description /by 2024-02-03", taskList);
            assertTrue(action instanceof Echo);

            // Test "event" command
            action = Parser.parseCommand("event Task description /from 2024-02-03 /to 2024-02-04", taskList);
            assertTrue(action instanceof Echo);

            // Test "mark" command
            action = Parser.parseCommand("mark 1", taskList);
            assertTrue(action instanceof Mark);

            // Test "unmark" command
            action = Parser.parseCommand("unmark 1", taskList);
            assertTrue(action instanceof Unmark);

            // Test "help" command
            action = Parser.parseCommand("help", taskList);
            assertTrue(action instanceof Help);

        } catch (Exception e) {
            fail("Exception thrown for valid command: " + e.getMessage());
        }
    }

    @Test
    void test_parseCommand_invalidCommands() {
        TaskList taskList = new TaskList();

        assertThrows(UnknownCommandException.class, () ->
                Parser.parseCommand("invalidCommand", taskList));

        assertThrows(NoIndexException.class, () ->
                Parser.parseCommand("mark", taskList));

        assertThrows(EmptyDescriptionException.class, () ->
                Parser.parseCommand("todo", taskList));

        assertThrows(WrongDateFormatException.class, () ->
                Parser.parseCommand("deadline Task description /by invalidDate", taskList));

        assertThrows(WrongDateOrderingException.class, () ->
                Parser.parseCommand("event Task description /from 2024-02-04 /to 2024-02-03", taskList));
    }
}

