package chatbot.task;

import chatbot.value.DateStringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void add_tasks_expectedBehaviour() {
        TaskList taskList = new TaskList();
        assertEquals("", taskList.toString());

        taskList.add(new ToDo("do todo"));
        assertEquals("1. [T][ ] do todo\n",
                taskList.toString());

        taskList.add(new Deadline("do deadline", DateStringValue.of("today")));
        assertEquals(
                "1. [T][ ] do todo\n" +
                        "2. [D][ ] do deadline (by: today)\n",
                taskList.toString());

        taskList.add(new Event("do event",
                DateStringValue.of("2pm"),
                DateStringValue.of("4pm")));
        assertEquals(
                "1. [T][ ] do todo\n" +
                        "2. [D][ ] do deadline (by: today)\n" +
                        "3. [E][ ] do event (from: 2pm to: 4pm)\n",
                taskList.toString());
    }

    @Test
    public void isEmpty_tasks_expectedBehaviour() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
        taskList.add(new ToDo("todo 1"));
        assertFalse(taskList.isEmpty());
    }
}
