package mona;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testInsert() {
        TaskList taskList = new TaskList(null);
        Todo toAdd = new Todo("first todo");
        Todo toAdd2 = new Todo("second todo");
        taskList.addTaskTest(toAdd);
        assertEquals(1, taskList.getNumberOfTasks());
        taskList.addTaskTest(toAdd2);
        assertEquals(2, taskList.getNumberOfTasks());
    }
    @Test
    public void testRemove() {
        TaskList taskList = new TaskList(null);
        Todo toAdd = new Todo("first todo");
        Todo toAdd2 = new Todo("second todo");
        taskList.addTaskTest(toAdd);
        taskList.addTaskTest(toAdd2);
        Task removed1 = taskList.deleteTaskTest(0);
        assertEquals("first todo", removed1.description);
        Task removed2 = taskList.deleteTaskTest(0);
        assertEquals("second todo", removed2.description);
    }
}
