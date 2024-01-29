package task;
import commands.TodoCommand;
import exception.TodoFormatException;
import org.junit.jupiter.api.Test;
import storage.StorageStub;
import ui.UiStub;

import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {

    @Test
    void execute_validMessage_success () throws TodoFormatException {
        // Arrange
        String todoMessage = "Buy groceries";
        TodoCommand todoCommand = new TodoCommand(todoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        // Act
        assertDoesNotThrow(() -> todoCommand.execute(taskList, storageStub, uiStub));

        // Assert
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.appendToFileCalled);
        assertTrue(uiStub.showToUserCalled);
    }

    @Test
    void execute_emptyMessage_exceptionThrown() {
        // Arrange
        String emptyTodoMessage = "";
        TodoCommand todoCommand = new TodoCommand(emptyTodoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        // Act
        try {
            todoCommand.execute(taskList, storageStub, uiStub);
            fail("Expected TodoFormatException, but it was not thrown.");
        } catch (TodoFormatException exception) {
            // Assert
            assertEquals("Uncle also need to know the message!\n\tCorrect Usage: todo <message>", exception.getMessage());

            // Verify that storage and UI interactions do not occur when the command is invalid
            assertFalse(storageStub.appendToFileCalled);
            assertFalse(uiStub.showToUserCalled);
        }
    }
}
