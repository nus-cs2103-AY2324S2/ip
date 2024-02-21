package duke;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import seedu.duke.command.ByeCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;





public class ByeTest {
    @Test
    public void testBye() throws StorageOperationException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);
        ByeCommand byeCommand = new ByeCommand();
        byeCommand.execute(mockTaskList, mockUi, mockStorage);

        verify(mockUi).generateGoodByeMessage();
        verify(mockStorage).save(mockTaskList);
    }
}
