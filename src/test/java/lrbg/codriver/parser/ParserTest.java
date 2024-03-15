package lrbg.codriver.parser;

import lrbg.codriver.command.ByeCommand;
import lrbg.codriver.command.DeadlineCommand;
import lrbg.codriver.command.DeleteCommand;
import lrbg.codriver.command.EventCommand;
import lrbg.codriver.command.ListCommand;
import lrbg.codriver.command.MarkCommand;
import lrbg.codriver.command.TodoCommand;
import lrbg.codriver.command.UnknownCommand;
import lrbg.codriver.command.UnmarkCommand;
import lrbg.codriver.command.FindCommand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Class for Parser.
 */
public class ParserTest {
    @Test
    public void parseTest_correctArgument_success() {
        try {
            assertTrue((new TodoCommand("1234"))
                    .testEquals(Parser.parse("todo 1234")));
            assertTrue((new DeadlineCommand("1234", Parser.parseDate("2021-08-25")))
                    .testEquals(Parser.parse("deadline 1234 /by 2021-08-25")));
            assertTrue((new EventCommand("1234",
                    Parser.parseDate("2021-08-25"), Parser.parseDate("2021-08-26")))
                    .testEquals(Parser.parse("event 1234 /from 2021-08-25 /to 2021-08-26")));
            assertTrue((new ByeCommand()).testEquals(Parser.parse("bye")));
            assertTrue((new UnknownCommand("blah")).testEquals(Parser.parse("blah blah blah")));
            assertTrue((new ListCommand()).testEquals(Parser.parse("list")));
            assertTrue((new DeleteCommand(1)).testEquals(Parser.parse("delete 1")));
            assertTrue((new MarkCommand(1)).testEquals(Parser.parse("mark 1")));
            assertTrue((new UnmarkCommand(1)).testEquals(Parser.parse("unmark 1")));
            assertTrue((new FindCommand("1234")).testEquals(Parser.parse("find 1234")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseTest_deadlineIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("deadline 1234 /by 202108-25");
            fail();
        } catch (Exception e) {
            assertEquals("Error! The date provided must be in yyyy-mm-dd format!", e.getMessage());
        }
        try {
            Parser.parse("deadline 1234");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You must provide a /by date for a deadline!", e.getMessage());
        }
        try {
            Parser.parse("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You cannot provide a deadline with no parameters!", e.getMessage());
        }
    }

    @Test
    public void parseTest_eventIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("event 1234 /from 202108-25 /to 2021-08-26");
            fail();
        } catch (Exception e) {
            assertEquals("Error! The date provided must be in yyyy-mm-dd format!", e.getMessage());
        }
        try {
            Parser.parse("event 1234 /from 2021-08-25 /to 202108-26");
            fail();
        } catch (Exception e) {
            assertEquals("Error! The date provided must be in yyyy-mm-dd format!", e.getMessage());
        }
        try {
            Parser.parse("event 1234 /from 2021-08-25 /to");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You must provide a /to date for an event!", e.getMessage());
        }
        try {
            Parser.parse("event 1234 /from 2021-08-25");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You must provide a /to date for an event!", e.getMessage());
        }
        try {
            Parser.parse("event 1234");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You must provide a /from date for an event!", e.getMessage());
        }
    }

    @Test
    public void parseTest_todoIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You cannot provide a todo with no description!", e.getMessage());
        }
    }

    @Test
    public void parseTest_markIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should provide an integer argument for mark!", e.getMessage());
        }
        try {
            Parser.parse("mark 1 2");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should only provide 1 argument for mark!", e.getMessage());
        }
    }

    @Test
    public void parseTest_unmarkIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should provide an integer argument for unmark!", e.getMessage());
        }
        try {
            Parser.parse("unmark 1 2");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should only provide 1 argument for unmark!", e.getMessage());
        }
    }

    @Test
    public void parseTest_deleteIncorrectArgument_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should provide an integer argument for delete!", e.getMessage());
        }
        try {
            Parser.parse("delete 1 2");
            fail();
        } catch (Exception e) {
            assertEquals("Error! You should only provide 1 argument for delete!", e.getMessage());
        }
    }
}