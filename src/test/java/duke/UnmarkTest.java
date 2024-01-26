package duke;

import duke.command.UnmarkCommand;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UnmarkTest {
    @Test
    public void testUnmark() throws InvalidInputException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);
        Task mockTask = mock(Task.class);
        when(mockTaskList.getListSize()).thenReturn(1);
        when(mockTaskList.getTask(0)).thenReturn(mockTask);

        new UnmarkCommand(1).execute(mockTaskList, mockUi, mockStorage);
        verify(mockTask).setHasDone(false);
        verify(mockUi).showMarkAsNotDone(mockTask);

        assertThrows(InvalidInputException.class, () -> new UnmarkCommand(0).execute(mockTaskList, mockUi, mockStorage));
    }
}
