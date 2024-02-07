package bytetalker.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testAddTodo_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo", "read", "book"};
        assertEquals("read book", tasks.addTodo(sampleMsg).getTask());
    }

    @Test
    public void testAddTodo_emptyTask1() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo"};
        assertEquals(null, tasks.addTodo(sampleMsg));
    }

    @Test
    public void testAddTodo_unsupportedFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo", "return", "book", "/by", "Sunday"};
        assertEquals(null, tasks.addTodo(sampleMsg));
    }

    @Test
    public void testAddDeadline_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"deadline", "read", "book", "/by", "5/2/2019", "1800"};
        assertEquals("[D][ ] read book (by: Feb 05 2019 6:00PM)", tasks.addDeadline(sampleMsg).toString());
    }

    @Test
    public void testAddDeadline_emptyMsg() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"deadline"};
        assertEquals(null, tasks.addDeadline(sampleMsg));
    }

    @Test
    public void testAddDeadline_wrongFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String sample = "deadline read book /by 5/2/2019 1800 /from 2/3/2019";
        sample = sample.strip();
        String[] sampleMsg = {"deadline", "read", "book", "/by", "5/2/2019", "1800", "/from", "2/3/2019"};
        assertEquals(null, tasks.addDeadline(sampleMsg));
    }

    @Test
    public void testAddEvent_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam", "/from", "8/2/2024", "1500", "/to", "8/2/2024", "1800"};
        assertEquals("[E][ ] midterm exam (from: Feb 08 2024 3:00PM to: Feb 08 2024 6:00PM)",
                tasks.addEvent(splitMessages).toString());
    }

    @Test
    public void testAddEvent_emptyMsg() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event"};
        assertEquals(null, tasks.addEvent(splitMessages));
    }

    @Test
    public void testAddEvent_wrongFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam", "/by", "Sunday"};
        assertEquals(null, tasks.addEvent(splitMessages));
    }

    @Test
    public void testAddEvent_missingField1() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam"};
        assertEquals(null, tasks.addEvent(splitMessages));
    }

    @Test
    public void testAddEvent_missingField2() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "mideterm", "exam", "/from", "5-2-2024"};
        assertEquals(null, tasks.addEvent(splitMessages));
    }
}
