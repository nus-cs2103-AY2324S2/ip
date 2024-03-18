package bytetalker.task;

import bytetalker.exception.UnsupportedDateTimeFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test() {
        assertEquals(2, 2);
    }

    @Test
    public void addTodo_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo", "read", "book"};
        assertEquals("read book", tasks.addTodo(sampleMsg).getTask());
    }

    @Test
    public void addTodo_emptyTask1() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo"};
        assertEquals(null, tasks.addTodo(sampleMsg));
    }

    @Test
    public void addTodo_unsupportedFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"todo", "return", "book", "/by", "Sunday"};
        assertEquals(null, tasks.addTodo(sampleMsg));
    }

    @Test
    public void addDeadline_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"deadline", "read", "book", "/by", "5/2/2019", "1800"};
        try {
            assertEquals("[D][ ] read book (by: Feb 05 2019 6:00PM)", tasks.addDeadline(sampleMsg).toString());
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addDeadline_emptyMsg() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] sampleMsg = {"deadline"};
        try {
            assertEquals(null, tasks.addDeadline(sampleMsg));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addDeadline_wrongFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String sample = "deadline read book /by 5/2/2019 1800 /from 2/3/2019";
        sample = sample.strip();
        String[] sampleMsg = {"deadline", "read", "book", "/by", "5/2/2019", "1800", "/from", "2/3/2019"};
        try {
            assertEquals(null, tasks.addDeadline(sampleMsg));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addEvent_sampleInput() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam", "/from", "8/2/2024", "1500", "/to", "8/2/2024", "1800"};
        try {
            assertEquals("[E][ ] midterm exam (from: Feb 08 2024 3:00PM to: Feb 08 2024 6:00PM)",
                    tasks.addEvent(splitMessages).toString());
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addEvent_emptyMsg() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event"};
        try {
            assertEquals(null, tasks.addEvent(splitMessages));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addEvent_wrongFormat() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam", "/by", "Sunday"};
        try {
            assertEquals(null, tasks.addEvent(splitMessages));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addEvent_missingField1() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "midterm", "exam"};
        try {
            assertEquals(null, tasks.addEvent(splitMessages));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }

    @Test
    public void addEvent_missingField2() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String[] splitMessages = {"event", "mideterm", "exam", "/from", "5-2-2024"};
        try {
            assertEquals(null, tasks.addEvent(splitMessages));
        } catch (UnsupportedDateTimeFormatException e) {
            assertEquals(true, false);
        }
    }
}
