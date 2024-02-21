package bozo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        taskList.removeTask(0);
        assertEquals(0, taskList.getSize());
    }
}
