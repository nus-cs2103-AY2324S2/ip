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

public class TodoCommandTest {

    @Test
    void execute_validMessage_success() {
        String todoMessage = "Buy groceries";
        TodoCommand todoCommand = new TodoCommand(todoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);

        assertDoesNotThrow(() -> todoCommand.execute(taskList, storageStub));
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.wasAppendToFileCalled());
    }

    @Test
    void execute_emptyMessage_exceptionThrown() {
        String emptyTodoMessage = "";
        TodoCommand todoCommand = new TodoCommand(emptyTodoMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);

        try {
            todoCommand.execute(taskList, storageStub);
            fail("Expected TodoFormatException, but it was not thrown.");
        } catch (TodoFormatException exception) {
            assertEquals("Uncle also need to know the message!\n\tCorrect Usage: todo <message>",
                    exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
        }
    }
}
