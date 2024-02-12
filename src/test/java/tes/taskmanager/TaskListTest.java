package tes.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


/**
 * Represents the testing class for the TaskList class.
 */
public class TaskListTest {
    /**
     * Tests the storeDeadline method in the TaskList class.
     */
    @Test
    public void testStoreDeadline() {
        String taskDescription = "return book";
        String by = "2022-12-12 1900";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.storeDeadline(taskDescription, by);
        String actual = taskList.getTaskDescription(0);
        assertEquals("D |   | return book | by: Dec 12 2022 07:00 PM", actual);
    }
}
