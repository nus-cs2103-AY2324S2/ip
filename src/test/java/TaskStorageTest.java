import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskStorage;
import task.ToDo;


public class TaskStorageTest {

    @Test
    public void taskStorageTest() {
        TaskStorage taskStorage = new TaskStorage();
        Task t1Task = new ToDo("Test");
        Task t2Task = new ToDo("Test 2");
        Task t3Task = new ToDo("Test 3");
        taskStorage.addTask(t1Task);
        taskStorage.addTask(t2Task);
        taskStorage.addTask(t3Task);
        assertEquals(taskStorage.size(), 3);
    }

    @Test
    public void taskStorageDeleteTest() {
        TaskStorage taskStorage = new TaskStorage();
        Task t1Task = new ToDo("Test");
        Task t2Task = new ToDo("Test 2");
        Task t3Task = new ToDo("Test 3");
        taskStorage.addTask(t1Task);
        taskStorage.addTask(t2Task);
        taskStorage.addTask(t3Task);
        taskStorage.removeTask(2);
        String checkAnswer = "1." + t1Task + "\n2." + t3Task;
        assertEquals(taskStorage.toString(), checkAnswer);
    }

    @Test
    public void taskStorageMarkTest() {
        TaskStorage taskStorage = new TaskStorage();
        Task t1Task = new ToDo("Test");
        Task t2Task = new ToDo("Test 2");
        Task t3Task = new ToDo("Test 3");
        taskStorage.addTask(t1Task);
        taskStorage.addTask(t2Task);
        taskStorage.addTask(t3Task);
        taskStorage.markTask(3, true);
        assertEquals(t3Task.getCompleted(), "1");
    }
}
