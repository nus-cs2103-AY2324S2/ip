package klee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import klee.task.Task;
import klee.task.ToDo;

public class TaskListTest {
    @Test
    public void testAddTask() {
        int expected = 0;
        TaskList tasks = new TaskList();
        int actual = tasks.size();
        assertEquals(expected, actual);
        tasks.add(new ToDo("test"));
        expected = 1;
        actual = tasks.size();
        assertEquals(expected, actual);
        tasks.add(new ToDo("test"));
        expected = 2;
        actual = tasks.size();
        assertEquals(expected, actual);
        tasks.add(new ToDo("test"));
        expected = 3;
        actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveTask() {
        int expected = 3;
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test"));
        int actual = tasks.size();
        assertEquals(expected, actual);
        tasks.remove(0);
        expected = 2;
        actual = tasks.size();
        assertEquals(expected, actual);
        tasks.remove(0);
        expected = 1;
        actual = tasks.size();
        assertEquals(expected, actual);
        tasks.remove(0);
        expected = 0;
        actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSize() {
        int expected = 1;
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        int actual = tasks.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTask() {
        ToDo expected = new ToDo("test");
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        Task actual = tasks.get(0);
        assertEquals(expected, actual);
    }
}
