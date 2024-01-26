package duke;

import duke.command.ListCommand;
import duke.common.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListTest {
    @Test
    public void testList() {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        new ListCommand().execute(mockTaskList, mockUi, mockStorage);

        verify(mockUi).showTaskList(mockTaskList);
    }
}
