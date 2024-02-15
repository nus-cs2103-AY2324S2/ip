package asher.Commands;

import org.junit.jupiter.api.Test;
import asher.BotException;
import asher.Tasks.Todo;
import asher.Tasks.Deadline;
import asher.Tasks.Event;
import asher.Commands.DeadlineCommand;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class parserTest {

    @Test
    public void testCreateToDoCommand() throws BotException {
        ToDoCommand toDoCommand = new asher.Commands.ToDoCommand("todo Go for a run");
        Todo todo = toDoCommand.createToDoCommand();
        assertEquals("Go for a run", todo.getDescription());
    }

    @Test
    public void testCreateDeadlineCommand() throws BotException {
        DeadlineCommand deadlineCommand = new asher.Commands.DeadlineCommand("deadline submit report /by 2023-12-31 18:00");
        Deadline deadline = deadlineCommand.createDeadlineCommand();
        assertEquals("submit report", deadline.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), deadline.getDueDate());
        assertEquals(LocalTime.of(18, 0), deadline.getDueTime());
    }

    @Test
    public void testCreateEventCommand() throws BotException {
        EventCommand eventCommand = new asher.Commands.EventCommand("event project meeting /from 2023-12-31 14:00 /to 2023-12-31 15:00");
        Event event = eventCommand.createEventCommand();
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), event.getStartDate());
        assertEquals(LocalTime.of(14, 0), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 31), event.getEndDate());
        assertEquals(LocalTime.of(15, 0), event.getEndTime());
    }
}
