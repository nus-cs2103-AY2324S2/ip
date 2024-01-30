package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_and_getSize() {
        Task task = new Todo("Read book");
        taskList.addTask(task);

        assertEquals(1, taskList.getSize(), "TaskList size should increase after adding a task.");
        assertEquals(task, taskList.getTask(0), "Added task should match the retrieved task.");
    }

    // ... add more test methods if necessary ...
}

