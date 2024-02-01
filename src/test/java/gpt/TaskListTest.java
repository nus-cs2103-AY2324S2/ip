package gpt;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testTaskListAddTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new Task("Sample Task", TaskType.E, false, "01/01/2021 1800", "01/01/2021 2000");

        assertEquals(0, taskList.size());

        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertTrue(taskList.containsTask(task));

    }

    @Test
    public void testMarkUnMarkTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new Task("Sample Task", TaskType.E, false, "01/01/2021 1800", "01/01/2021 2000");
        assertEquals(0, taskList.size());
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.get(0).isDone());
        taskList.unmarkTask(0);
        assertFalse(taskList.get(0).isDone());
    }
}