package duke;

import duke.command.DeadlineCommand;
import duke.common.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String description = "test";
        Storage mockStorage = mock(Storage.class);
        Ui  mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        new DeadlineCommand(description, localDateTime).execute(mockTaskList, mockUi, mockStorage);
        verify(mockTaskList).addTask(argThat(task -> {
            assertTrue(task instanceof Deadline);
            assertEquals(task.getDescription(), description);
            assertFalse(task.getHasDone());
            assertEquals(((Deadline)task).getDeadline(), localDateTime);
            return true;
        }));
    }
}
