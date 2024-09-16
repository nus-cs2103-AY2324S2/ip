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
        TaskList taskList = new TaskList(new TasksUi(new ByteArrayOutputStream(), new ByteArrayOutputStream()));

        // List is currently empty
        assertEquals(0, taskList.getListSize());

        // List now has 1 task
        taskList.store(new Task(""));
        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void store_fullList_exceptionThrown() throws Exception {
        TaskList taskList = new TaskList(new TasksUi(new ByteArrayOutputStream(), new ByteArrayOutputStream()));
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
        // @@author ronnnnnnnnn-reused
        // Reused from https://stackoverflow.com/a/32241300 with minor modifications
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // @@author

        TaskList taskList = new TaskList(new TasksUi(out, new ByteArrayOutputStream()));
        taskList.store(new Task("return book"));
        out.reset();

        taskList.listTasks();

        // Lists the newly added task
        assertEquals("_____________________________________________________\r\n" +
                "Here are the tasks in your list:\r\n" +
                "[ ] return book\r\n" +
                "_____________________________________________________\r\n", out.toString());
    }

    @Test
    public void listTasks_empty_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new TasksUi(new ByteArrayOutputStream(), new ByteArrayOutputStream()));
            taskList.listTasks();
            fail(); // The test should not reach this line as the task list is empty
        } catch (Exception e) {
            assertEquals("Your todo list is currently empty.", e.getMessage());
        }
    }
}
