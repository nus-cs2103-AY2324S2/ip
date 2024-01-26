package duke;

import duke.command.ByeCommand;
import duke.common.TaskList;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ByeTest {
    @Test
    public void testBye() throws StorageOperationException {
        Storage mockStorage = mock(Storage.class);
        Ui  mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);
        ByeCommand byeCommand = new ByeCommand();
        byeCommand.execute(mockTaskList, mockUi, mockStorage);

        verify(mockUi).showGoodBye();
        verify(mockStorage).save(mockTaskList);
    }
}
