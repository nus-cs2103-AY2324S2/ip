package command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import iggly.command.AddTaskCommand;
import iggly.command.MarkTaskCommand;
import iggly.command.UnmarkTaskCommand;
import iggly.duke.Storage;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.model.ToDo;

public class MarkTaskCommandTest {
    @AfterEach
    public void cleanup() {
        File file = new File("test.dat");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
    @Test
    public void executeMarkTaskCommandTest() {
        ToDo todo = new ToDo("todo");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test.dat");

        AddTaskCommand addTaskCommand = new AddTaskCommand(todo, taskList);
        addTaskCommand.execute(storage);
        Task task = taskList.get(0);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(task, taskList);
        markTaskCommand.execute(storage);

        assertTrue(todo.isDone());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(task, taskList);
        unmarkTaskCommand.execute(storage);

        assertFalse(todo.isDone());
    }
}
