package checkbot.command;

import checkbot.Storage;
import checkbot.StorageStub;
import checkbot.Ui;
import checkbot.UiStub;
import checkbot.task.TodoList;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void createTodo_success() throws Exception {
        TodoList todoList = new TodoList();
        Storage storage = new StorageStub();
        Ui ui = new UiStub();

        Command c = new AddCommand("test");
        c.execute(todoList, storage, ui);

        // TODO: Override .equals method for Tasks
        assertEquals(todoList.getTask(0).formatForFile(), "T | 0 | test");
    }
}
