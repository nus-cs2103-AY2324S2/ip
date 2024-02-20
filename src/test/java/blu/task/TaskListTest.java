package blu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blu.exception.IllegalParameterException;

public class TaskListTest {
    private TaskList taskList;
    private Task testTask1;
    private Task testTask2;
    private Task testTask3;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        testTask1 = new ToDo("test todo1");
        testTask2 = new ToDo("test todo2");
        testTask3 = new ToDo("test todo3");
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        taskList.addTask(testTask3);
    }

    @Test
    public void deleteTask_invalidTaskNumber_exceptionThrown() {
        assertThrows(IllegalParameterException.class, () -> taskList.deleteTask(-1));
        assertThrows(IllegalParameterException.class, () -> taskList.deleteTask(10000));
    }

    @Test
    public void deleteTask_validTaskNumber_success() throws IllegalParameterException {
        taskList.deleteTask(3);
        assertEquals(2, taskList.getNumberOfTasks());
        taskList.deleteTask(2);
        assertEquals(1, taskList.getNumberOfTasks());
        taskList.deleteTask(1);
        assertEquals(0, taskList.getNumberOfTasks());
    }

    @Test
    public void getTask_invalidTaskNumber_exceptionThrown() {
        assertThrows(IllegalParameterException.class, () -> taskList.getTask(-1));
        assertThrows(IllegalParameterException.class, () -> taskList.getTask(10000));
    }

    @Test
    public void getTask_validTaskNumber_success() throws IllegalParameterException {
        Task retrievedTask1 = taskList.getTask(1);
        assertEquals(testTask1, retrievedTask1);
        Task retrievedTask2 = taskList.getTask(2);
        assertEquals(testTask2, retrievedTask2);
        Task retrievedTask3 = taskList.getTask(3);
        assertEquals(testTask3, retrievedTask3);
    }
}
