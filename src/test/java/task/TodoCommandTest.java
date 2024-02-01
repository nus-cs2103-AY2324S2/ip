package task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import commands.TodoCommand;
import exception.TodoFormatException;
import storage.StorageStub;
import ui.UiStub;

public class TodoCommandTest {

    @Test
    void execute_validMessage_success() throws TodoFormatException {
        String todoMessage = "Buy groceries";
        TodoCommand todoCommand = new TodoCommand(todoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        assertDoesNotThrow(() -> todoCommand.execute(taskList, storageStub, uiStub));
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.wasAppendToFileCalled());
        assertTrue(uiStub.showToUserCalled);
    }

    @Test
    void execute_emptyMessage_exceptionThrown() {
        String emptyTodoMessage = "";
        TodoCommand todoCommand = new TodoCommand(emptyTodoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        try {
            todoCommand.execute(taskList, storageStub, uiStub);
            fail("Expected TodoFormatException, but it was not thrown.");
        } catch (TodoFormatException exception) {
            assertEquals("Uncle also need to know the message!\n\tCorrect Usage: todo <message>",
                    exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
            assertFalse(uiStub.showToUserCalled);
        }
    }
}
