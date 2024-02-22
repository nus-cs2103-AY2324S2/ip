package nollid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nollid.tasks.Todo;

public class TaskListTest {
    @Test
    public void add_singleTask_success() {
        TaskList taskList = new TaskList();

        taskList.add(new Todo("test"));

        int expected = 1;
        int actual = taskList.size();

        assertEquals(expected, actual);
    }

    @Test
    public void add_multipleTasks_success() {
        TaskList taskList = new TaskList();

        Todo task1 = new Todo("test");
        Todo task2 = new Todo("test");
        Todo task3 = new Todo("test");

        taskList.add(task1, task2, task3);

        int expected = 3;
        int actual = taskList.size();

        assertEquals(expected, actual);
    }
}
