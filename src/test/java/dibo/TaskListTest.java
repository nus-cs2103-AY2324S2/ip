package dibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dibo.task.Task;
import dibo.task.ToDo;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ArrayList<Task> store = new ArrayList<>();
        taskList = new TaskList(store);
    }

    @Test
    public void testAddTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        taskList.deleteTask(1);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testMarkTask() {
        Task task1 = new ToDo("A");
        taskList.addTask(task1);
        taskList.markTask(1);
        assertTrue(task1.isDone());
    }
    @Test
    public void testUnmarkTask() {
        Task task1 = new ToDo("A");
        task1.markAsDone();
        taskList.addTask(task1);
        taskList.unmarkTask(1);
        assertFalse(task1.isDone());
    }
}
