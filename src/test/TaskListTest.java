package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    public void removeTask_and_verifySizeDecrease() {
        Task task1 = new Todo("Read book");
        Task task2 = new Todo("Write essay");
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.removeTask(0);

        assertEquals(1, taskList.getSize(), "TaskList size should decrease after removing a task.");
        assertEquals(task2, taskList.getTask(0), "Remaining task should be 'Write essay'.");
    }

    @Test
    public void markTaskAsDone_and_verifyStatus() {
        Task task = new Todo("Read book");
        taskList.addTask(task);

        taskList.getTask(0).markAsDone();

        assertTrue(taskList.getTask(0).isDone, "Task should be marked as done.");
    }

    @Test
    public void unmarkTaskAsDone_and_verifyStatus() {
        Task task = new Todo("Read book");
        task.markAsDone();
        taskList.addTask(task);

        taskList.getTask(0).markAsNotDone();

        assertFalse(taskList.getTask(0).isDone, "Task should be marked as not done.");
    }

    @Test
    public void findTasks_withMatchingKeyword() {
        Task task1 = new Todo("Read book");
        Task task2 = new Deadline("Submit assignment", LocalDateTime.now());
        Task task3 = new Event("Project meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        var matchingTasks = taskList.findTasks("book");

        assertEquals(1, matchingTasks.size(), "Should find one task with 'book' in its description.");
        assertTrue(matchingTasks.contains(task1), "The list should contain the task with 'Read book' description.");
    }
}

