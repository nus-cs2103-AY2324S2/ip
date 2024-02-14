package duke.main;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ViewCommand;
import duke.command.ToDoCommand;
import duke.command.UnMarkCommand;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParserTest {

    @Test
    void parserTest() throws DukeException  {
        assertTrue(Parser.parse("deadline return book /by 2019-10-15 1800") instanceof DeadlineCommand, "deadline parser passed");
        assertTrue(Parser.parse("delete 50") instanceof DeleteCommand, "delete parser passed");
        assertTrue(Parser.parse("event write essay /from 2019-10-15 1800 /to 2019-10-17 2000") instanceof EventCommand, "event parser passed");
        assertTrue(Parser.parse("bye") instanceof ExitCommand, "exit parser passed");
        assertTrue(Parser.parse("list") instanceof ListCommand, "list parser passed");
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand, "mark parser passed");
        assertTrue(Parser.parse("view Oct 15 2019") instanceof ViewCommand, "view parser passed");
        assertTrue(Parser.parse("view Oct 17 2019") instanceof ViewCommand, "view parser passed");
        assertTrue(Parser.parse("todo go to school") instanceof ToDoCommand, "todo parser passed");
        assertTrue(Parser.parse("unmark 20") instanceof UnMarkCommand, "unmark parser passed");
    }
}
