package jerry;
import jerry.command.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private final Parser parser = new Parser(new Ui(), new TaskList(new ArrayList<Task>()));

    @Test
    void parse_ReturnsToDoCommand_ForValidToDoInput() {
        String input = "todo read book";
        Command command = parser.parse(input);
        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    void parse_ReturnsMarkCommand_ForValidMarkInput() {
        String input = "mark 1";
        Command command = parser.parse(input);
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void parse_ReturnsUnmarkCommand_ForValidUnmarkInput() {
        String input = "unmark 2";
        Command command = parser.parse(input);
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    void parse_HandlesUnknownCommands_Gracefully() {
        String input = "sing a song";
        Command command = parser.parse(input);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parse_AddDeadlineCommandWithDescriptionAndDate_ReturnsAddDeadlineCommand() {
        Command command = parser.parse("deadline Submit assignment /by 2023-10-10");
        assertEquals(AddDeadlineCommand.class, command.getClass());

    }

    @Test
    void parse_AddEventCommandWithDescriptionAndDateTime_ReturnsAddEventCommand() {
        Command command = parser.parse("event Project meeting /at 2023-10-10 14:00 to 16:00");
        assertEquals(AddEventCommand.class, command.getClass());
    }
}