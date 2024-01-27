package duke;

import duke.command.DeleteCommand;
import duke.common.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

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
        verify(mockUi).showDeleteTask(mockTask, mockTaskList);

        assertThrows(InvalidInputException.class, () ->
                new DeleteCommand(0).execute(mockTaskList, mockUi, mockStorage));
    }
}
