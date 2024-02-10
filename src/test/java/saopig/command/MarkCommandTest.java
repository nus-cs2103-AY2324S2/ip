package saopig.command;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saopig.Saopig;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

public class MarkCommandTest {
    private TaskList mockTaskList;
    private Ui mockUi;
    private Storage mockStorage;
    private Task mockTask;
    private MarkCommand markCommand;

    @BeforeEach
    void setUp() {
        mockTaskList = mock(TaskList.class);
        mockUi = mock(Ui.class);
        mockStorage = mock(Storage.class);
        mockTask = mock(Task.class);
    }

    @Test
    void testUnmarkTaskAsDoneInvalidInputLength() {
        String inputCommand = "unmark"; // Too short, missing index
        String result;
        markCommand = new MarkCommand(inputCommand, 1);

        result = markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        assert result.contains("Oopses daisy!");
    }

    @Test
    void testUnmarkTaskAsDoneIndexOutOfBounds() {
        String inputCommand = "unmark 999"; // Assuming this index is out of range
        String result;
        when(mockTaskList.getTask(anyInt())).thenThrow(new IndexOutOfBoundsException());
        markCommand = new MarkCommand(inputCommand, 1);
        result = markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        assert result.contains("invalid index for the task list");
    }

    @Test
    void testUnmarkTaskAsDoneInvalidIndexFormat() {
        String inputCommand = "unmark two"; // Non-numeric index
        String result;
        markCommand = new MarkCommand(inputCommand, 1);
        result = markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        assert result.contains("invalid index for the task list");
    }
}
