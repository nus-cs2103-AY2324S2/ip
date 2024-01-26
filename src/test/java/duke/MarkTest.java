package duke;

import duke.command.MarkCommand;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MarkTest {
    @Test
    public void testMark() throws InvalidInputException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);
        Task mockTask = mock(Task.class);


        when(mockTaskList.getListSize()).thenReturn(1);
        when(mockTaskList.getTask(0)).thenReturn(mockTask);

        new MarkCommand(1).execute(mockTaskList, mockUi, mockStorage);

        verify(mockTask).setHasDone(true);
        verify(mockUi).showMaskAsDone(mockTask);

        assertThrows(InvalidInputException.class, () -> new MarkCommand(0).execute(mockTaskList, mockUi, mockStorage));
    }
}
