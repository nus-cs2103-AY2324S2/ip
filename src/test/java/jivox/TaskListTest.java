package jivox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jivox.task.Task;
import jivox.task.TaskList;
import jivox.task.Todo;


public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        task1 = new Todo("Take out trash");
        task2 = new Todo("Do laundry");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        taskList = new TaskList(tasks);
    }

    @Test
    public void testDelete() {
        taskList.delete(0);
        assertEquals(1, taskList.getLength());
        assertSame(task2, taskList.getTask(0));
    }

    @Test
    public void testGetLength() {
        assertEquals(2, taskList.getLength());
    }

    @Test
    public void testAdd() {
        Task task3 = new Todo("Walk dog");
        taskList.add(task3);
        assertEquals(3, taskList.getLength());
        assertSame(task3, taskList.getTask(2));
    }

    @Test
    public void testGetTask() {
        assertSame(task1, taskList.getTask(0));
        assertSame(task2, taskList.getTask(1));
    }
}
