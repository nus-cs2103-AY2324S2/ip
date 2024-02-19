import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskStorage;
import task.ToDo;


public class TaskStorageTest {

    @Test
    public void taskStorage_addThreeTask_success() {
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
    public void taskStorage_addThreeTaskAndDeleteSecond_success() {
        TaskStorage taskStorage = new TaskStorage();
        Task t1Task = new ToDo("Test");
        Task t2Task = new ToDo("Test 2");
        Task t3Task = new ToDo("Test 3");
        taskStorage.addTask(t1Task);
        taskStorage.addTask(t2Task);
        taskStorage.addTask(t3Task);
        taskStorage.removeTask(2);
        String checkAnswer = "You have 2 tasks, they are:\n1." + t1Task + "\n2." + t3Task;
        assertEquals(taskStorage.toString(), checkAnswer);
    }

    @Test
    public void taskStorage_addThreeTaskAndMarkThird_success() {
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
