package command;

import duke.Storage;
import model.TaskList;
import model.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(0, taskList);
        markTaskCommand.execute(storage);

        assertTrue(todo.isDone());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(0, taskList);
        unmarkTaskCommand.execute(storage);

        assertFalse(todo.isDone());
    }
}
