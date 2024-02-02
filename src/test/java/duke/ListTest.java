package duke;

import seedu.duke.command.ListCommand;
import seedu.duke.common.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
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

        verify(mockUi).generateTaskList(mockTaskList);
    }
}
