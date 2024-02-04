package badgpt.util;

import badgpt.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void store_notFull_success() throws Exception {
        TaskList taskList = new TaskList();

        // List is currently empty
        assertEquals(0, taskList.getListSize());

        // List now has 1 task
        taskList.store(new Task(""));
        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void store_fullList_exceptionThrown() throws Exception {
        TaskList taskList = new TaskList();
        // Add 100 tasks to fill up the list
        for (int i = 0; i < 100; i++) {
            taskList.store(new Task(""));
        }

        // The next store operation should fail
        try {
            taskList.store(new Task(""));
            fail();
        } catch (Exception e) {
            assertEquals("Your todo list is currently full.", e.getMessage());
        }
    }

    @Test
    public void listTasks_nonEmpty_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.store(new Task("return book"));

        // @@author ronnnnnnnnn-reused
        // Reused from https://stackoverflow.com/a/32241300 with minor modifications
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // @@author

        taskList.listTasks();

        // Lists the newly added task
        assertEquals("____________________________________________________________\r\n" +
                "Here are the tasks in your list:\r\n" +
                "1. [ ] return book\r\n" +
                "____________________________________________________________\r\n", out.toString());
    }

    @Test
    public void listTasks_empty_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.listTasks();
            fail(); // The test should not reach this line as the task list is empty
        } catch (Exception e) {
            assertEquals("Your todo list is currently empty.", e.getMessage());
        }
    }
}
