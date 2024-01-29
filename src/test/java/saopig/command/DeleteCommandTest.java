package saopig.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

class DeleteCommandTest {

    private TaskList mockTaskList;
    private Ui mockUi;
    private Storage mockStorage;
    private DeleteCommand deleteCommand;

    @BeforeEach
    public void setUp() {
        mockTaskList = mock(TaskList.class);
        mockUi = mock(Ui.class);
        mockStorage = mock(Storage.class);
    }

    @Test
    public void testDeleteTaskSuccessfully() {
        String inputCommand = "delete 1";
        deleteCommand = new DeleteCommand(inputCommand, 0);

        // Mocking the necessary behavior
        when(mockTaskList.getSize()).thenReturn(1); // Assuming there is one task
        when(mockTaskList.getTask(anyInt())).thenReturn(new Deadline("return book",
                LocalDateTime.parse("2020-02-02 18:00",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));

        deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify task is deleted and storage is updated
        verify(mockTaskList).deleteTask(anyInt());
        verify(mockStorage).saveTaskList(any(TaskList.class));

        // Verify UI message
        verify(mockUi).printMessage(contains("has been deleted successfully"));
    }

    @Test
    void testInputTooShort() {
        String inputCommand = "delete"; // Too short, missing index
        deleteCommand = new DeleteCommand(inputCommand, 0);

        deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify that an error message is shown
        verify(mockUi).printMessage(contains("Oopses daisy!"));
    }

    @Test
    void testNonNumericInput() {
        String inputCommand = "delete xyz"; // Non-numeric index
        deleteCommand = new DeleteCommand(inputCommand, 0);

        deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify that an error message is shown
        verify(mockUi).printMessage(contains("Oopses daisy!"));
    }

    @Test
    void testOutOfRangeIndex() {
        String inputCommand = "delete 999"; // Assuming this index is out of range
        deleteCommand = new DeleteCommand(inputCommand, 0);

        when(mockTaskList.getSize()).thenReturn(5); // Assuming list size is 5

        deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify that an error message is shown
        verify(mockUi).printMessage(contains("Oopses daisy!"));
    }
}
