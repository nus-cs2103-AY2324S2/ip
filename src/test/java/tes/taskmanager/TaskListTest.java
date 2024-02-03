package tes.taskmanager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testStoreDeadLine() {
        String taskDescription = "return book";
        String by = "2022-12-12 1900";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.storeDeadline(taskDescription, by);
        String actual = taskList.getTaskDescription(0);
        assertEquals("D |   | return book | by: Dec 12 2022 07:00 PM", actual);
    }
}