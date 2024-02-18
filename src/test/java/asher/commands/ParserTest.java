package asher.commands;

import org.junit.jupiter.api.Test;
import asher.BotException;
import asher.tasks.Todo;
import asher.tasks.Deadline;
import asher.tasks.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testCreateToDoCommand() throws BotException {
        ToDoCommand toDoCommand = new asher.commands.ToDoCommand("todo Go for a run");
        Todo todo = toDoCommand.createToDoCommand();
        assertEquals("Go for a run", todo.getDescription());
    }

    @Test
    public void testCreateDeadlineCommand() throws BotException {
        DeadlineCommand deadlineCommand = new asher.commands.DeadlineCommand("deadline submit report /by 2023-12-31 18:00");
        Deadline deadline = deadlineCommand.createDeadlineCommand();
        assertEquals("submit report", deadline.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), deadline.getDueDate());
        assertEquals(LocalTime.of(18, 0), deadline.getDueTime());
    }

    @Test
    public void testCreateEventCommand() throws BotException {
        EventCommand eventCommand = new asher.commands.EventCommand("event project meeting /from 2023-12-31 14:00 /to 2023-12-31 15:00");
        Event event = eventCommand.createEventCommand();
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDate.of(2023, 12, 31), event.getStartDate());
        assertEquals(LocalTime.of(14, 0), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 31), event.getEndDate());
        assertEquals(LocalTime.of(15, 0), event.getEndTime());
    }
}
