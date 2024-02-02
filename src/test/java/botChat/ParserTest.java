package botChat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void testGetCommandValid() {
        assertEquals(Command.LIST, Parser.getCommand("list"));
        assertEquals(Command.BYE, Parser.getCommand("bye"));
        assertEquals(Command.MARK, Parser.getCommand("mark"));
        assertEquals(Command.UNMARK, Parser.getCommand("unmark"));
        assertEquals(Command.EVENT, Parser.getCommand("event"));
        assertEquals(Command.DEADLINE, Parser.getCommand("deadline"));
        assertEquals(Command.TODO, Parser.getCommand("todo"));
        assertEquals(Command.DELETE, Parser.getCommand("delete"));
    }

    @Test
    public void testGetCommandUnknown() {
        assertEquals(Command.UNKNOWN, Parser.getCommand("invalid"));
        assertEquals(Command.UNKNOWN, Parser.getCommand(""));
    }

    @Test
    public void testConvertTaskTodo() {
        String todoLine = "[T] [X] Sample Todo";
        Task todoTask = Parser.convertTask(todoLine);

        assertTrue(todoTask instanceof Todo);
        assertEquals("Sample Todo", todoTask.getDescription());
        assertEquals("X", todoTask.getStatusIcon());
    }

    @Test
    public void testConvertTaskDeadline() {
        String deadlineLine = "[D] [ ] Project (by: 2024-02-29)";
        Task deadlineTask = Parser.convertTask(deadlineLine);

        assertTrue(deadlineTask instanceof Deadline);
        assertEquals("Project", deadlineTask.getDescription());
        assertEquals(" ", deadlineTask.getStatusIcon());
        assertEquals("Feb 29 2024", ((Deadline) deadlineTask).getBy());
    }

    @Test
    public void testConvertTaskEvent() {
        String eventLine = "[E] [X] Team Meeting (from: 2024-02-01 to: 2024-02-02)";
        Task eventTask = Parser.convertTask(eventLine);

        assertTrue(eventTask instanceof Event);
        assertEquals("Team Meeting", eventTask.getDescription());
        assertEquals("X", eventTask.getStatusIcon());
        assertEquals("Feb 01 2024", ((Event) eventTask).getFrom());
        assertEquals("Feb 02 2024", ((Event) eventTask).getTo());
    }

    @Test
    public void testConvertTaskInvalid() {
        String invalidLine = "[I][ ] Invalid Task";
        Task invalidTask = Parser.convertTask(invalidLine);

        assertNull(invalidTask);
    }
}
