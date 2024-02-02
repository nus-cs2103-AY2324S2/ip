package duke;

import seedu.duke.command.CheckCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class CheckTest {
    @Test
    public void testCheck() throws DukeException {
        LocalDate localDate = LocalDate.parse("2022-01-01", DateTimeFormatter.ofPattern("yyy-MM-dd"));
        Deadline deadline1 = new Deadline("hello", false, localDate.atStartOfDay());
        Deadline deadline2 = new Deadline("hello1", true, localDate.atStartOfDay());
        Deadline deadline3 = new Deadline("hello2", false, LocalDateTime.now());

        TaskList mockTaskList = mock(TaskList.class);
        Ui mockUi = mock(Ui.class);
        Storage mockStorage = mock(Storage.class);

        when(mockTaskList.getListSize()).thenReturn(3);
        when(mockTaskList.getTask(0)).thenReturn(deadline1);
        when(mockTaskList.getTask(1)).thenReturn(deadline2);
        when(mockTaskList.getTask(2)).thenReturn(deadline3);

        new CheckCommand(localDate).execute(mockTaskList, mockUi, mockStorage);

        verify(mockUi).generateDueTaskListResponse(argThat(list -> list.contains(deadline1)), eq(localDate));
    }
}
