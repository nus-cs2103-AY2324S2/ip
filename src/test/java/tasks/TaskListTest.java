package tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void test_addTask() {
        Task task = new Task("Buy groceries", false);
        taskList.addTask(task);
        List<Task> tasks = taskList.getTaskList();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void test_size() {
        assertEquals(0, taskList.size());
        Task task1 = new Task("Buy groceries", false);
        Task task2 = new Task("Submit report", true, "2022-10-31");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
    }

    @Test
    public void test_showList() {
        Task task1 = new Task("Buy groceries", false);
        Task task2 = new Task("Submit report", true, "2022-10-31");
        taskList.addTask(task1);
        taskList.addTask(task2);
        String expected = "1. [T][ ] Buy groceries\n2. [D][X] Submit report (2022-10-31)\n";
        assertEquals(expected, taskList.showList());
    }

    @Test
    public void test_getTaskAtIndex() {
        Task task1 = new Task("Buy groceries", false);
        Task task2 = new Task("Submit report", true, "2022-10-31");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(task1, taskList.getTaskAtIndex(0));
        assertEquals(task2, taskList.getTaskAtIndex(1));
    }

    @Test
    public void test_deleteAtIndex() {
        Task task1 = new Task("Buy groceries", false);
        Task task2 = new Task("Submit report", true, "2022-10-31");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteAtIndex(0);
        List<Task> tasks = taskList.getTaskList();
        assertEquals(1, tasks.size());
        assertEquals(task2, tasks.get(0));
    }

    @Test
    public void test_markTask() {
        Task task = new Task("Buy groceries", false);
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(task.isDone);
    }

    @Test
    public void test_unmarkTask() {
        Task task = new Task("Buy groceries", true);
        taskList.addTask(task);
        taskList.unmarkTask(0);
        assertFalse(task.isDone);
    }

    @Test
    public void test_addDeadlineTask() {
        taskList.addDeadlineTask("Submit report", "2022-10-31");
        List<Task> tasks = taskList.getTaskList();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals("[D][ ] Submit report (2022-10-31)", task.toString());
    }

    @Test
    public void test_addEventTask() {
        taskList.addEventTask("Birthday party", "2022-12-25", "2022-12-26");
        List<Task> tasks = taskList.getTaskList();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals("[E][ ] Birthday party (2022-12-25 to 2022-12-26)", task.toString());
    }
}