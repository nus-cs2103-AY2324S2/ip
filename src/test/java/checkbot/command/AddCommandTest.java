package checkbot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import checkbot.Storage;
import checkbot.StorageStub;
import checkbot.Ui;
import checkbot.UiStub;
import checkbot.exception.DuplicateTaskException;
import checkbot.task.Deadline;
import checkbot.task.Event;
import checkbot.task.Todo;
import checkbot.task.TodoList;

public class AddCommandTest {
    @Test
    public void createTodo_success() throws Exception {
        TodoList todoList = new TodoList();
        Storage storage = new StorageStub();
        Ui ui = new UiStub();

        Command c = new AddCommand("test");
        c.executeCommand(todoList, storage, ui);

        assertEquals(todoList.getTask(0), new Todo("test"));
    }

    @Test
    public void createDeadline_success() throws Exception {
        TodoList todoList = new TodoList();
        Storage storage = new StorageStub();
        Ui ui = new UiStub();

        Command c = new AddCommand("buy flowers", "14 Feb 2024");
        c.executeCommand(todoList, storage, ui);

        assertEquals(todoList.getTask(0), new Deadline("buy flowers", "14 Feb 2024"));
    }

    @Test
    public void createEvent_success() throws Exception {
        TodoList todoList = new TodoList();
        Storage storage = new StorageStub();
        Ui ui = new UiStub();

        Command c = new AddCommand("japan trip", "02 May 2024", "09 May 2024");
        c.executeCommand(todoList, storage, ui);

        assertEquals(todoList.getTask(0), new Event("japan trip", "02 May 2024", "09 May 2024"));
    }

    @Test
    public void createTodo_duplicateExceptionThrown() throws Exception {
        TodoList todoList = new TodoList();
        Storage storage = new StorageStub();
        Ui ui = new UiStub();

        Command c = new AddCommand("test");
        c.executeCommand(todoList, storage, ui);
        try {
            c.executeCommand(todoList, storage, ui);
        } catch (DuplicateTaskException e) {
            assertEquals(e.getMessage(), new DuplicateTaskException(new Todo("test")).getMessage());
        }
    }
}
