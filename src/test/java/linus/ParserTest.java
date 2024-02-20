package linus;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    // Adapted from AI and @quelinxiao
    // test the method parseTask of Parser class
    @Test
    public void testParseTaskTodo() {
        String todoLine = "T | 1 | Finish homework";
        Task todoTask = Parser.parseTask(todoLine);

        assertTrue(todoTask instanceof Todo);
        assertEquals("Finish homework", todoTask.getDescription());
        assertTrue(todoTask.isDone());
    }

    @Test
    public void testParseTaskEvent() {
        String eventLine = "E | 0 | Project Linus | 2024-01-15 | 2024-02-29";
        Task eventTask = Parser.parseTask(eventLine);

        assertTrue(eventTask instanceof Event);
        assertEquals("Project Linus", eventTask.getDescription());
        assertFalse(eventTask.isDone());
        assertEquals(LocalDate.parse("2024-01-15"), ((Event) eventTask).getFrom());
        assertEquals(LocalDate.parse("2024-02-29"), ((Event) eventTask).getTo());
    }

    @Test
    public void testParseTaskDeadline() {
        String deadlineLine = "D | 0 | Finish Project Linus | 2024-02-29";
        Task deadlineTask = Parser.parseTask(deadlineLine);

        assertTrue(deadlineTask instanceof Deadline);
        assertEquals("Finish Project Linus", deadlineTask.getDescription());
        assertFalse(deadlineTask.isDone());
        assertEquals(LocalDate.parse("2024-02-29"), ((Deadline) deadlineTask).getBy());
    }
}