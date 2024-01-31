package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList list;

    @BeforeEach
    void setUpEmptyList() {
        list = new TaskList();
        // Mocking Ui methods or ensure that Ui methods can run in a test environment
    }

    @Test
    public void addTask_SampleTask_taskAddedToList() {
        Task task = new Todo("read novel");
        list.addTask(task);
        assertEquals(1, list.size(), "TaskList size should increase after adding a task");
    }

    @Test
    public void deleteTask_afterRemovingTask_decreasesSize() {
        Task task1 = new Todo("watch movie");
        Task task2 = new Todo("read ebook");
        list.addTask(task1);
        list.addTask(task2);

        list.deleteTask(0);
        assertEquals(1, list.size(), "Size should be 1 after removing one task");
    }
}