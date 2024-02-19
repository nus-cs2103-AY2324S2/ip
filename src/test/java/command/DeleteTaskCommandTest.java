package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import iggly.command.AddTaskCommand;
import iggly.command.DeleteTaskCommand;
import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.model.ToDo;

public class DeleteTaskCommandTest {
    private static TaskList taskList;
    private static Storage storage;

    @BeforeAll
    static void setUp() {
        taskList = new TaskList();
        storage = new Storage("test.dat");

        ToDo todo = new ToDo("todo");

        AddTaskCommand addTaskCommand = new AddTaskCommand(todo, taskList);
        addTaskCommand.execute(storage);
    }
    @AfterEach
    public void cleanup() {
        File file = new File("test.dat");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    public void executeDeleteTaskTest() {
        assertEquals(1, taskList.size());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(0, taskList);
        deleteTaskCommand.execute(storage);
        ArrayList<Task> updatedTaskList = taskList.getTaskList();
        assertEquals(0, updatedTaskList.size());
    }
}
