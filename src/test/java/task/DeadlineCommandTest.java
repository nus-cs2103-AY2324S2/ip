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
import ui.UiStub;

public class DeadlineCommandTest {

    @Test
    void execute_validMessage_success() throws DeadlineFormatException {
        String deadlineMessage = "Complete project /by 2022-12-31";
        DeadlineCommand deadlineCommand = new DeadlineCommand(deadlineMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        assertDoesNotThrow(() -> deadlineCommand.execute(taskList, storageStub, uiStub));
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.wasAppendToFileCalled());
        assertTrue(uiStub.wasShowToUserCalled());
    }

    @Test
    void execute_missingByField_exceptionThrown() {
        String missingByFieldMessage = "Complete project";
        DeadlineCommand deadlineCommand = new DeadlineCommand(missingByFieldMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        try {
            deadlineCommand.execute(taskList, storageStub, uiStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
            assertFalse(uiStub.wasShowToUserCalled());
        }
    }

    @Test
    void execute_invalidDateFormat_exceptionThrown() {
        String invalidDateFormatMessage = "Complete project /by invalid-date";
        DeadlineCommand deadlineCommand = new DeadlineCommand(invalidDateFormatMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        try {
            deadlineCommand.execute(taskList, storageStub, uiStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());
            assertFalse(storageStub.wasAppendToFileCalled());
            assertFalse(uiStub.wasShowToUserCalled());
        }
    }

}
