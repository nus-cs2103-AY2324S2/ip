package task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import commands.DeadlineCommand;
import exception.DeadlineFormatException;
import storage.StorageStub;

public class DeadlineCommandTest {

    @Test
    void execute_validMessage_success() {
        String deadlineMessage = "Complete project /by 2022-12-31";
        DeadlineCommand deadlineCommand = new DeadlineCommand(deadlineMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);

        assertDoesNotThrow(() -> deadlineCommand.execute(taskList, storageStub));
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.wasAppendToFileCalled());
    }

    @Test
    void execute_missingByField_exceptionThrown() {
        String missingByFieldMessage = "Complete project";
        DeadlineCommand deadlineCommand = new DeadlineCommand(missingByFieldMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);

        try {
            deadlineCommand.execute(taskList, storageStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
        }
    }

    @Test
    void execute_invalidDateFormat_exceptionThrown() {
        String invalidDateFormatMessage = "Complete project /by invalid-date";
        DeadlineCommand deadlineCommand = new DeadlineCommand(invalidDateFormatMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);

        try {
            deadlineCommand.execute(taskList, storageStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
        }
    }
}
