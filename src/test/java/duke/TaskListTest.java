package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("Adding a task increases task list size")
    void testAddTask() {
        Task task = new Todo("Read book", "todo Read book");
        taskList.addTask(task);
        assertEquals(1, TaskList.storageFill, "Task should be added to list");
    }

    @Test
    @DisplayName("Deleting a task decreases task list size")
    void testDeleteTask() {
        Task task = new Todo("Read book", "todo Read book");
        int prevCount = TaskList.storageFill;
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(prevCount, TaskList.storageFill, "Task should be deleted from list");
    }

    @Test
    @DisplayName("Retrieving a task returns the correct task")
    void testGetTask() {
        Task task = new Todo("Read book", "todo Read book");
        taskList.addTask(task);
        Task retrievedTask = taskList.getTask(0);
        assertEquals(task, retrievedTask, "Task should be identical");
    }
}
