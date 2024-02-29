package util;

import exceptions.ChillChiefException;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getAllTasks_arrayListOfTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todoTask = new Todo("eat", false);
        LocalDateTime by = LocalDateTime.of(2024, 6, 1, 16, 30);
        Deadline deadlineTask = new Deadline("test", false, by);
        Collections.addAll(tasks,todoTask, deadlineTask);
        TaskList taskList = new TaskList(tasks);
        assertEquals(tasks, taskList.getAllTasks());
    }

    @Test
    public void getTask_taskObject_success() throws ChillChiefException {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todoTask = new Todo("eat", false);
        LocalDateTime by = LocalDateTime.of(2024, 6, 1, 16, 30);
        Deadline deadlineTask = new Deadline("test", false, by);
        Collections.addAll(tasks,todoTask, deadlineTask);
        TaskList taskList = new TaskList(tasks);
        assertEquals(todoTask, taskList.getTask(0));
    }

    @Test
    public void getTask_taskObject_exceptionThrown() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Todo todoTask = new Todo("eat", false);
            LocalDateTime by = LocalDateTime.of(2024, 6, 1, 16, 30);
            Deadline deadlineTask = new Deadline("test", false, by);
            Collections.addAll(tasks,todoTask, deadlineTask);
            TaskList taskList = new TaskList(tasks);
            assertEquals(todoTask, taskList.getTask(3));
            fail();
        } catch (Exception e) {
            assertEquals("Your index is out of bounds!", e.getMessage());
        }
    }
}
