package duke;

import seedu.duke.command.EventCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventTest {
    @Test
    public void testEvent() throws InvalidInputException, DuplicateTaskException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        String description = "hello";
        LocalDateTime startDate = LocalDateTime.parse("2022-10-10 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDate = LocalDateTime.parse("2022-10-11 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        new EventCommand(description, startDate, endDate).execute(mockTaskList, mockUi, mockStorage);
        verify(mockUi).generateNewTaskResponse(argThat(task -> {
            assertTrue(task instanceof Event);
            assertEquals(task.getDescription(), description);
            assertEquals(((Event) task).getStartDate(), startDate);
            assertEquals(((Event) task).getEndDate(), endDate);
            return true;
        }), eq(mockTaskList));
        assertThrows(InvalidInputException.class,
                () -> new EventCommand(description, LocalDateTime.now(), endDate).execute(mockTaskList, mockUi,
                        mockStorage));

    }
}
