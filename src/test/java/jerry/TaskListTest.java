package jerry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    void addTask_ShouldAddTaskToList() {
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().contains(task));
    }

    @Test
    void deleteTask_ShouldRemoveTaskFromList() {
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        taskList.deleteTask(0); // Adjust if your method requires an index or different parameter
        assertTrue(taskList.getTasks().isEmpty());
    }
}
