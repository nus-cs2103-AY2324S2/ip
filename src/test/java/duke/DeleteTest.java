package duke;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import seedu.duke.command.DeleteCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;





public class DeleteTest {
    @Test
    public void testDelete() throws DukeException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);
        when(mockTaskList.getListSize()).thenReturn(1);
        Task mockTask = mock(Task.class);
        when(mockTaskList.getTask(0)).thenReturn(mockTask);

        new DeleteCommand(1).execute(mockTaskList, mockUi, mockStorage);
        verify(mockTaskList).deleteTask(0);
        verify(mockUi).generateDeleteTaskResponse(mockTask, mockTaskList);

        assertThrows(InvalidInputException.class, () ->
                new DeleteCommand(0).execute(mockTaskList, mockUi, mockStorage));
    }
}
