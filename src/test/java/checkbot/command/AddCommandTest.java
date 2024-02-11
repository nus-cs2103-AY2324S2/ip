package checkbot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import checkbot.Storage;
import checkbot.StorageStub;
import checkbot.Ui;
import checkbot.UiStub;
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
}
