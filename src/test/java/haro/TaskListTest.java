package haro;

import haro.exception.InvalidArgsException;
import haro.task.Deadline;
import haro.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import haro.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskListTest {
    private ArrayList<Task> tasks;
    @BeforeEach
    public void init() {
        tasks = new ArrayList<>();
        tasks.add(new ToDo("test"));
        tasks.add(new Deadline("test2", "today"));
    }
    @Test
    public void taskList_markNegativeIndex_invalidArgsExceptionThrown() {
        try {
            TaskList taskList = new TaskList(tasks);
            taskList.markTask(-5);
            fail(); // the test should not reach here
        } catch (InvalidArgsException e) {
            assertEquals("Please input a positive task number!\n", e.getMessage());
        } catch (Exception e) {
            fail(); // the test should not reach here
        }
    }

    @Test
    public void taskList_deleteTaskIndexMoreThanSize_invalidArgsExceptionThrown() {
        try {
            TaskList taskList = new TaskList(tasks);
            taskList.deleteTask(5);
            fail(); // the test should not reach here
        } catch (InvalidArgsException e) {
            assertEquals("Sorry that item does not exist in your list!\n", e.getMessage());
        } catch (Exception e) {
            fail(); // the test should not reach here
        }
    }
}
