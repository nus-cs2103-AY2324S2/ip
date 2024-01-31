package slaybot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entity.ToDo;


public class TaskListTest {
    @Test
    public void testRemoveTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("test"));

        int initialSize = tasks.getSize();
        tasks.removeTask(0);

        assertEquals(initialSize - 1, tasks.getSize());
    }
}
