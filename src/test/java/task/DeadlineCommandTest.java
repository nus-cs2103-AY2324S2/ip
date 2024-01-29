package task;
import commands.DeadlineCommand;
import commands.TodoCommand;
import exception.DeadlineFormatException;
import exception.TodoFormatException;
import org.junit.jupiter.api.Test;
import storage.StorageStub;
import ui.UiStub;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineCommandTest {

    @Test
    void execute_validMessage_success() throws DeadlineFormatException {
        // Arrange
        String deadlineMessage = "Complete project /by 2022-12-31";
        DeadlineCommand deadlineCommand = new DeadlineCommand(deadlineMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        // Act
        assertDoesNotThrow(() -> deadlineCommand.execute(taskList, storageStub, uiStub));

        // Assert
        assertEquals(1, taskList.numTasks());
        assertTrue(storageStub.appendToFileCalled);
        assertTrue(uiStub.showToUserCalled);
    }

    @Test
    void execute_missingByField_exceptionThrown() {
        // Arrange
        String missingByFieldMessage = "Complete project";
        DeadlineCommand deadlineCommand = new DeadlineCommand(missingByFieldMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        // Act
        try {
            deadlineCommand.execute(taskList, storageStub, uiStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            // Assert
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());

            // Verify that storage and UI interactions do not occur when the command is invalid
            assertFalse(storageStub.appendToFileCalled);
            assertFalse(uiStub.showToUserCalled);
        }
    }

    @Test
    void execute_invalidDateFormat_exceptionThrown() {
        // Arrange
        String invalidDateFormatMessage = "Complete project /by invalid-date";
        DeadlineCommand deadlineCommand = new DeadlineCommand(invalidDateFormatMessage);
        TaskList taskList = new TaskList();
        StorageStub storageStub = new StorageStub(taskList);
        UiStub uiStub = new UiStub();

        // Act
        try {
            deadlineCommand.execute(taskList, storageStub, uiStub);
            fail("Expected DeadlineFormatException, but it was not thrown.");
        } catch (DeadlineFormatException exception) {
            // Assert
            assertEquals("Correct Usage: deadline <message> /by <day/time>", exception.getMessage());

            // Verify that storage and UI interactions do not occur when the command is invalid
            assertFalse(storageStub.appendToFileCalled);
            assertFalse(uiStub.showToUserCalled);
        }
    }

}
