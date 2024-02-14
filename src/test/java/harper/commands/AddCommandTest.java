package harper.commands;

import harper.tasks.ToDo;
import harper.utils.StorageStub;
import harper.utils.TaskList;
import harper.utils.UiStub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    @Test
    public void execute_storageSuccessfullySave_success() {
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub("src/test", "test.txt");
        UiStub ui = new UiStub();
        ToDo task = new ToDo("read book", false);
        Command addCommand = new AddCommand(task);

        assertDoesNotThrow(() -> addCommand.execute(taskList, ui, storageStub));
        assertTrue(ui.isPrintSuccessfulAddCalled);
    }
}
