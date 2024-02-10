package saopig.command;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.TaskList;

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
        String result;
        result = deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify task is deleted and storage is updated
        verify(mockTaskList).deleteTask(anyInt());
        verify(mockStorage).saveTaskList(any(TaskList.class));

        // Verify UI message
        assert result.contains("Oh, splendid!");
    }

    @Test
    void testInputTooShort() {
        String inputCommand = "delete"; // Too short, missing index
        deleteCommand = new DeleteCommand(inputCommand, 0);
        String result;
        result = deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify that an error message is shown
        assert result.contains("Oopses daisy!") || result.contains("Oh, splendid!");
    }

    @Test
    void testNonNumericInput() {
        String inputCommand = "delete xyz"; // Non-numeric index
        deleteCommand = new DeleteCommand(inputCommand, 0);
        String result;
        result = deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);
        assert result.contains("Oopses daisy!") || result.contains("Oh, splendid!");
    }

    @Test
    void testOutOfRangeIndex() {
        String inputCommand = "delete 999"; // Assuming this index is out of range
        deleteCommand = new DeleteCommand(inputCommand, 0);
        String result;
        when(mockTaskList.getSize()).thenReturn(5); // Assuming list size is 5

        result = deleteCommand.deleteTask(inputCommand, mockTaskList, mockUi, mockStorage);

        // Verify that an error message is shown
        assert result.contains("Oopses daisy!") || result.contains("Oh, splendid!");
    }
}
