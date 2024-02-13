package seedu.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    public TaskList sampleTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("drink bbt"));
        LocalDate date = LocalDate.parse("15/02/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        tasks.add(new Deadline("make bbt",  date));
        Task event = new Event("sell bbt", "tdy", "tmr");
        event.markDone();
        tasks.add(event);

        return new TaskList(tasks);
    }

    @Test
    public void deleteTaskTest() {
        // Delete task 1.
        TaskList sampleList = sampleTaskList();
        Task deletedTask = sampleList.getTask(0);
        sampleList.deleteTask(0);
        // Test if task 1 is indeed deleted.
        assertFalse(sampleList.getTasks().contains(deletedTask));
    }

    @Test
    public void addTaskTest() {
        // Add a todo task.
        TaskList sampleList = sampleTaskList();
        Task taskToAdd = new Todo("buy milk");
        sampleList.addTask(taskToAdd);
        // Test if the todo task is indeed added to the list.
        assertTrue(sampleList.getTasks().contains(taskToAdd));
    }

    @Test
    public void markTaskTest() {
        // Mark task 2 done.
        TaskList sampleList = sampleTaskList();
        sampleList.markTask(1);
        // Test if task 2 is indeed marked done.
        assertEquals("[x]", sampleList.getTask(1).toString().substring(0, 3));
    }

    @Test
    public void unmarkTaskTest() {
        // Mark the completed task 3 undone.
        TaskList sampleList = sampleTaskList();
        sampleList.unmarkTask(2);
        // Test if task 3 is indeed marked undone.
        assertEquals("[ ]", sampleList.getTask(2).toString().substring(0, 3));
    }
}
