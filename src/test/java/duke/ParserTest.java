package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.others.BelleException;
import duke.run.Parser;


public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void parseDeleteTest() {
        try {
            assertEquals(true, parser.parse("delete 2") instanceof DeleteCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseListTest() {
        try {
            assertEquals(true, parser.parse("List") instanceof ListCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseByeTest() {
        try {
            assertEquals(true, parser.parse("bye") instanceof ByeCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseMarkTest() {
        try {
            assertEquals(true, parser.parse("mark 2") instanceof MarkCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseUnmarkTest() {
        try {
            assertEquals(true, parser.parse("unmark 2") instanceof UnmarkCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseToDoTest() {
        try {
            assertEquals(true, parser.parse("todo 2") instanceof AddTaskCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseDeadlineTest() {
        try {
            assertEquals(true, parser.parse("deadline return book /by Sunday\n") instanceof AddTaskCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseEventTest() {
        try {
            assertEquals(true, parser.parse("event project meeting /from Mon 2pm /to 4pm") instanceof AddTaskCommand);
        } catch (BelleException e) {
            System.out.println(e.getMessage());
        }
    }
}
