package duke;

import seedu.duke.command.DeadlineCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeadlineTest {
    @Test
    public void testDeadline() throws DuplicateTaskException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String description = "test";
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        new DeadlineCommand(description, localDateTime).execute(mockTaskList, mockUi, mockStorage);
        verify(mockTaskList).addTask(argThat(task -> {
            assertTrue(task instanceof Deadline);
            assertEquals(task.getDescription(), description);
            assertFalse(task.getHasDone());
            assertEquals(((Deadline) task).getDeadline(), localDateTime);
            return true;
        }));
    }
}
