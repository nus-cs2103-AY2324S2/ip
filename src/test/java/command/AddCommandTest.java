package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;

public class AddCommandTest {
    private Storage storage;
    private TaskList tasks;
    private TaskList testList;

    public AddCommandTest() {
        this.storage = new Storage("");
        this.tasks = new TaskList();
        this.testList = new TaskList();
        this.testList.addTask(new Todo("task"));
    }
    @Test
    public void execute_normalInput_success() {
        Task todo = new Todo("task");
        AddCommand add = new AddCommand(todo);
        add.execute(tasks, storage);
        assertEquals(tasks.getTask(0).toString(), testList.getTask(0).toString());
    }

}
