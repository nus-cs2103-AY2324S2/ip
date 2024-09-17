package brian.parser;

import brian.command.*;
import brian.utils.BrianException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parse() throws Exception {
        assertEquals(Parser.parse("list").getClass(), ListCommand.class);

        assertEquals(Parser.parse("mark 1").getClass(), MarkCommand.class);
        BrianException markException = assertThrows(BrianException.class, () -> Parser.parse("mark"));
        assertEquals(markException.getMessage(), "The id of a mark cannot be empty.");

        assertEquals(Parser.parse("unmark 1").getClass(), UnmarkCommand.class);
        BrianException unmarkException = assertThrows(BrianException.class, () -> Parser.parse("unmark"));
        assertEquals(unmarkException.getMessage(), "The id of an unmark cannot be empty.");

        assertEquals(Parser.parse("todo 1").getClass(), TodoCommand.class);
        BrianException todoException = assertThrows(BrianException.class, () -> Parser.parse("todo"));
        assertEquals(todoException.getMessage(), "The description of a todo cannot be empty.");

        assertEquals(Parser.parse("deadline 1 /by 25/08/2025").getClass(), DeadlineCommand.class);
        BrianException deadlineDescException = assertThrows(BrianException.class, () -> Parser.parse("deadline"));
        assertEquals(deadlineDescException.getMessage(), "The description of a deadline cannot be empty.");
        BrianException deadlineByException = assertThrows(BrianException.class, () -> Parser.parse("deadline description"));
        assertEquals(deadlineByException.getMessage(), "The deadline of a deadline cannot be empty.");

        assertEquals(Parser.parse("event 1 /from 25/08/2025 /to 26/08/2025").getClass(), EventCommand.class);
        BrianException eventDescException = assertThrows(BrianException.class, () -> Parser.parse("event"));
        assertEquals(eventDescException.getMessage(), "The description of an event cannot be empty.");
        BrianException eventFromException = assertThrows(BrianException.class, () -> Parser.parse("event desc"));
        assertEquals(eventFromException.getMessage(), "The from of an event cannot be empty.");
        BrianException eventToException = assertThrows(BrianException.class, () -> Parser.parse("event desc /from 25/08/2025"));
        assertEquals(eventToException.getMessage(), "The to of an event cannot be empty.");

        assertEquals(Parser.parse("delete 1").getClass(), DeleteCommand.class);
        BrianException deleteException = assertThrows(BrianException.class, () -> Parser.parse("delete"));
        assertEquals(deleteException.getMessage(), "The id of a delete cannot be empty.");

        assertEquals(Parser.parse("bye").getClass(), ByeCommand.class);
        assertEquals(Parser.parse("help").getClass(), HelpCommand.class);
        assertEquals(Parser.parse("asdasdad").getClass(), HelpCommand.class);



    }
}