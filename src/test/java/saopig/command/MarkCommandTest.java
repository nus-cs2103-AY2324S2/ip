package saopig.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

import static org.mockito.Mockito.*;

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
    void testMarkTaskAsDoneValidInput() {
        String inputCommand = "mark 1";
        when(mockTaskList.getTask(anyInt())).thenReturn(mockTask);
        markCommand = new MarkCommand(inputCommand, 0);

        markCommand.markTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        verify(mockTask).markAsDone();
        verify(mockStorage).saveTaskList(any(TaskList.class));
        verify(mockUi).printMessage(contains("has been marked as done successfully"));
    }

    @Test
    void testUnmarkTaskAsDoneValidInput() {
        String inputCommand = "unmark 1";
        when(mockTaskList.getTask(anyInt())).thenReturn(mockTask);
        markCommand = new MarkCommand(inputCommand, 1);

        markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        verify(mockTask).unmarkAsDone();
        verify(mockStorage).saveTaskList(any(TaskList.class));
        verify(mockUi).printMessage(contains("you've unmarked task"));
    }

    @Test
    void testUnmarkTaskAsDoneInvalidInputLength() {
        String inputCommand = "unmark"; // Too short, missing index
        markCommand = new MarkCommand(inputCommand, 1);

        markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        verify(mockUi).printMessage(contains("Oopses daisy!"));
    }

    @Test
    void testUnmarkTaskAsDoneIndexOutOfBounds() {
        String inputCommand = "unmark 999"; // Assuming this index is out of range
        when(mockTaskList.getTask(anyInt())).thenThrow(new IndexOutOfBoundsException());
        markCommand = new MarkCommand(inputCommand, 1);
        markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        verify(mockUi).printMessage(contains("invalid index for the task list"));
    }

    @Test
    void testUnmarkTaskAsDoneInvalidIndexFormat() {
        String inputCommand = "unmark two"; // Non-numeric index
        markCommand = new MarkCommand(inputCommand, 1);
        markCommand.unmarkTaskAsDone(inputCommand, mockTaskList, mockUi, mockStorage);

        verify(mockUi).printMessage(contains("invalid index for the task list"));
    }
}
