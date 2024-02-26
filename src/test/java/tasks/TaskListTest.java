package tasks;
import tasks.Deadline;
import org.junit.jupiter.api.Test;
import tasks.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList t = new TaskList();
        assertEquals("", t.toString());
        Deadline d = new Deadline("sleep", "2024-10-10 21:00");
        assertEquals(String.format("    Ok! I have added your task:\n      %s\n    You have %d task(s) in the "
                + "list now.\n\n", d.toString(), 1), t.addTask(d));
        assertEquals(String.format("    1.%s\n", d.toString()), t.toString());
    }

    @Test
    public void testRemoveTask() {
        TaskList t = new TaskList();
        Deadline d = new Deadline("Finish assignment", "2023-11-30 22:59");
        t.addTask(d);
        assertEquals(String.format("    Ok, I have removed your task:\n    %s\n    You have %d task(s) in the "
                + "list now.\n\n", d.toString(), 0), t.removeTask(0));
        assertEquals("", t.toString());
    }
}
