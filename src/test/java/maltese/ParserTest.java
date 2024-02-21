package maltese;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import maltese.action.Action;
import maltese.action.Delete;
import maltese.action.Echo;
import maltese.action.Farewell;
import maltese.action.Help;
import maltese.action.Mark;
import maltese.action.TaskList;
import maltese.action.Unmark;
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
        Storage storage = new Storage("./data/test.txt");

        try {
            // Test "bye" command
            action = Parser.parseCommand("bye", taskList, storage);
            assertTrue(action instanceof Farewell);

            // Test "list" command
            action = Parser.parseCommand("list", taskList, storage);
            assertTrue(action instanceof TaskList);


            // Test "todo" command
            action = Parser.parseCommand("todo Task description", taskList, storage);
            assertTrue(action instanceof Echo);

            // Test "delete" command
            action = Parser.parseCommand("delete 1", taskList, storage);
            assertTrue(action instanceof Delete);

            // Test "deadline" command
            action = Parser.parseCommand("deadline Task description /by 2024-02-03", taskList,
                    storage);
            assertTrue(action instanceof Echo);

            // Test "event" command
            action = Parser.parseCommand("event Task description /from 2024-02-03 /to 2024-02-04"
                    , taskList, storage);
            assertTrue(action instanceof Echo);

            // Test "mark" command
            action = Parser.parseCommand("mark 1", taskList, storage);
            assertTrue(action instanceof Mark);

            // Test "unmark" command
            action = Parser.parseCommand("unmark 1", taskList, storage);
            assertTrue(action instanceof Unmark);

            // Test "help" command
            action = Parser.parseCommand("help", taskList, storage);
            assertTrue(action instanceof Help);

        } catch (Exception e) {
            fail("Exception thrown for valid command: " + e.getMessage());
        }
    }

    @Test
    void test_parseCommand_exceptionThrown() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test.txt");

        assertThrows(UnknownCommandException.class, () ->
                Parser.parseCommand("invalidCommand", taskList, storage));

        assertThrows(NoIndexException.class, () ->
                Parser.parseCommand("mark", taskList, storage));

        assertThrows(EmptyDescriptionException.class, () ->
                Parser.parseCommand("todo", taskList, storage));

        assertThrows(WrongDateFormatException.class, () ->
                Parser.parseCommand("deadline Task description /by invalidDate", taskList,
                        storage));

        assertThrows(WrongDateOrderingException.class, () ->
                Parser.parseCommand("event Task description /from 2024-02-04 /to 2024-02-03",
                        taskList, storage));
    }
}

