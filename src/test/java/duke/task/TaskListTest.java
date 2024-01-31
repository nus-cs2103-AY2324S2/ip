package duke.task;

import duke.exception.DukeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    void addTask_validTask_taskAdded() {
        Task task = new Task(TaskType.T, "Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void addToDoTask_validToDoTask_toDoTaskAdded() {
        ToDo toDoTask = null;
        try {
            toDoTask = new ToDo("Test ToDo Task");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

        taskList.addToDoTask(toDoTask);
        assertEquals(1, taskList.size());
        assertEquals(toDoTask, taskList.getTask(0));
    }

    @Test
    void deleteTask_validIndex_taskDeleted() {

        Task task = new Task(TaskType.T, "Test Task");

        taskList.addTask(task);

        assertDoesNotThrow(() -> taskList.deleteTask(0));
        assertEquals(0, taskList.size());
    }

}
