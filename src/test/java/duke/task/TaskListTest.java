package duke.task;

import duke.exceptions.IllegalParamException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_successful() {
        // add a todo task
        Task task = Task.createTask(TaskType.TODO, "test code", false);
        taskList.add(task);
        assertEquals(1, taskList.countTasks());

        // add another deadline line
        LocalDate date = LocalDate.of(2002, 2, 21);
        Task task1 = Task.createTask(TaskType.DEADLINE, "test code", false, date);
        taskList.add(task1);
        assertEquals(2, taskList.countTasks());

    }

    @Test
    public void getTask_validIndex_returnsTask() throws IllegalParamException {
        // adding and retrieving task should return same object
        Task task = Task.createTask(TaskType.TODO, "test", false);
        taskList.add(task);
        Task retrievedTask = taskList.getTask(1);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        // getting task from empty taskList throws exception
        assertThrows(IllegalParamException.class, () -> taskList.getTask(1));

        // getting index 0 throws exception
        assertThrows(IllegalParamException.class, () -> taskList.getTask(0));

        // getting task 2 when only 1 task exist throws exception
        Task task = Task.createTask(TaskType.TODO, "test", false);
        taskList.add(task);
        assertThrows(IllegalParamException.class, () -> taskList.getTask(2));
    }

    @Test
    public void deleteTask_validIndex_successful() throws IllegalParamException {
        // deleting a task removes it
        Task task = Task.createTask(TaskType.TODO, "test", false);
        taskList.add(task);
        if (taskList.countTasks() != 1) {
            fail(); // verify that task was added
        }
        taskList.deleteTask(1);
        assertEquals(0, taskList.countTasks());
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        // delete index 1 from empty list throws exception
        assertThrows(IllegalParamException.class, () -> taskList.deleteTask(1));

        // delete index 0 from list throws exception
        assertThrows(IllegalParamException.class, () -> taskList.deleteTask(0));

        // delete task 2 when only 1 task exist throws exception
        Task task = Task.createTask(TaskType.TODO, "test", false);
        taskList.add(task);
        assertThrows(IllegalParamException.class, () -> taskList.deleteTask(2));
    }

    @Test
    public void countTasks_emptyList_returnsZero() {
        assertEquals(0, taskList.countTasks());
    }

    @Test
    public void toString_nonEmptyList_returnsCorrectFormattedString() {
        // toString method returns expected string
        Task task = Task.createTask(TaskType.TODO, "test", false);
        LocalDate date = LocalDate.of(2002, 2, 21);
        Task task2 = Task.createTask(TaskType.DEADLINE, "test", false, date);

        taskList.add(task);
        taskList.add(task2);

        String expected = "Here is the list of things I remember!\n1.[T][ ] test\n2.[D][ ] test (by: 21 Feb 2002)\n";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void toString_emptyList_returnsDefaultMessage() {
        String expected = "Looks like you have nothing to do! Yay!\n";
        assertEquals(expected, taskList.toString());
    }
}
