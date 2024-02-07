package asher.Commands;

import org.junit.jupiter.api.Test;
import asher.BotException;
import asher.Tasks.Todo;
import asher.Tasks.Deadline;
import asher.Tasks.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class parserTest {

    @Test
    public void testCreateToDoCommand() throws BotException {
        Todo todo = Parser.createToDoCommand("todo Go for a run");
        assertEquals("Go for a run", todo.getDescription());
    }

    @Test
    public void testCreateDeadlineCommand() throws BotException {
        Deadline deadline = Parser.createDeadlineCommand("deadline submit report /by 2023-12-31 18:00");
        assertEquals("submit report", deadline.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), deadline.getDueDate());
        assertEquals(LocalTime.of(18, 0), deadline.getDueTime());
    }

    @Test
    public void testCreateEventCommand() throws BotException {
        Event event = Parser.createEventCommand("event project meeting /from 2023-12-31 14:00 /to 2023-12-31 15:00");
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), event.getStartDate());
        assertEquals(LocalTime.of(14, 0), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 31), event.getEndDate());
        assertEquals(LocalTime.of(15, 0), event.getEndTime());
    }
}
