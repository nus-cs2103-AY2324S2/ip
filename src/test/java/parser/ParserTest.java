package parser;
import command.Command;
import command.DeadlineCmd;
import command.EventCmd;
import dukeexceptions.DeadlineEmptyException;
import dukeexceptions.EventEmptyException;
import dukeexceptions.InvalidCmd;
import dukeexceptions.InvalidDateTimeFormat;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void testParseCommand() throws InvalidDateTimeFormat, InvalidCmd, EventEmptyException, DeadlineEmptyException {
        testParseDeadlineCmd();
        testParseEventCmd();
    }

    @Test
    public void testParseDeadlineCmd() throws InvalidDateTimeFormat, InvalidCmd, EventEmptyException, DeadlineEmptyException {
        Deadline expected = new Deadline("pick bread", LocalDateTime.of(1212,12,12,12,12));
        Command actual = parser.parseCommand("deadline pick bread /by 12-12-1212 12:12");
        if (actual instanceof DeadlineCmd) {
            assertEquals(expected, ((DeadlineCmd) actual).getTask());
        } else {
            assertEquals(expected, "Actual is not a DeadlineCmd object");
        }
    }

    @Test
    public void testParseEventCmd() throws InvalidDateTimeFormat, InvalidCmd, EventEmptyException, DeadlineEmptyException {
        Event expected = new Event(
                "pick bread",
                LocalDateTime.of(1212,12,12,12,12),
                LocalDateTime.of(1212,12,13,12,12));
        Command actual = parser.parseCommand("event pick bread /from 12-12-1212 12:12 /to 13-12-1212 12:12");
        if (actual instanceof EventCmd) {
            assertEquals(expected, ((EventCmd) actual).getTask());
        } else {
            assertEquals(expected, "Actual is not a EventCmd object");
        }
    }
}
