package duke.main;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.OnCommand;
import duke.command.ToDoCommand;
import duke.command.UnMarkCommand;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class ParserTest {

    //test instanceof
    @Test
    void parserTest() throws DukeException  {
        assertTrue(Parser.parse("deadline return book /by 2019-10-15 1800") instanceof DeadlineCommand);
        assertTrue(Parser.parse("delete 50") instanceof DeleteCommand);
        assertTrue(Parser.parse("event write essay /from 2019-10-15 1800 /to 2019-10-17 2000") instanceof EventCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("on Oct 15 2019") instanceof OnCommand);
        assertTrue(Parser.parse("on Oct 17 2019") instanceof OnCommand);
        assertTrue(Parser.parse("todo go to school") instanceof ToDoCommand);
        assertTrue(Parser.parse("unmark 20") instanceof UnMarkCommand);
    }
}
