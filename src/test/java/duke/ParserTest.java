package duke;

import duke.command.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void testParseBye() {
        assertEquals(Parser.parse("bye"), new ExitCommand());
    }
    @Test
    public void testParseList() {
        assertEquals(Parser.parse("list"), new ListCommand());
    }
    @Test
    public void testParseMark() {
        assertEquals(Parser.parse("mark 1"), new MarkCommand(0));
    }
    @Test
    public void testParseUnMark() {
        assertEquals(Parser.parse("unmark 1"), new UnMarkCommand(0));
    }
    @Test
    public void testParseDelete() {
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(0));
    }
    @Test
    public void testParseTodo() {
        assertEquals(Parser.parse("todo hello"), new TodoCommand("hello"));
    }
    @Test
    public void testParseDeadline() {
        //event project meeting /from 2/12/2019 1800 /to 2/12/2019 1800
        assertEquals(Parser.parse("deadline return book /by 20/12/2019 1800"),
                new DeadlineCommand("return book",
                        LocalDateTime.of(
                                LocalDate.of(2019, 12, 20),
                                LocalTime.of(18, 0)
                        )
                )
        );
    }
    @Test
    public void testParseEvent() {
        LocalDateTime from = LocalDateTime.of(
                LocalDate.of(2019, 12, 2),
                LocalTime.of(18, 0)
        );
        assertEquals(Parser.parse("event project meeting /from 2/12/2019 1800 /to 2/12/2019 1800"),
                new EventCommand("project meeting",
                    from, from
                )
        );
    }
}
