package duke;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import seedu.duke.command.MarkCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;





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
        verify(mockUi).generateMarkAsDoneResponse(mockTask);

        assertThrows(InvalidInputException.class, () -> new MarkCommand(0).execute(mockTaskList, mockUi, mockStorage));
    }
}
