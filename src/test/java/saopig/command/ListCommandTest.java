package saopig.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ListCommandTest {
    private TaskList mockTaskList;
    private Ui mockUi;
    private ListCommand listCommand;
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        mockTaskList = mock(TaskList.class);
        mockUi = mock(Ui.class);
        mockStorage = mock(Storage.class);
    }

    @Test
    void testListTasksWithEmptyTaskList() {
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>());
        listCommand = new ListCommand("", 0); // typeIndex 0 for listing all tasks

        listCommand.listTasks(mockTaskList, mockUi);

        // Verify that the message for an empty task list is printed
        verify(mockUi).printMessage(contains("no tasks yet"));
    }

    @Test
    void testListTasksWithNonEmptyTaskList() {
        // Assuming Task is an interface or class with a proper toString implementation
        Task mockTask = mock(Task.class);
        when(mockTask.toString()).thenReturn("Task Details");
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>(Arrays.asList(mockTask, mockTask)));

        listCommand = new ListCommand("", 0);

        listCommand.listTasks(mockTaskList, mockUi);

        // Verify that task details are printed for each task
        verify(mockUi, times(2)).printMessage(contains("Task Details"));
    }

    @Test
    void testListTasksOnDateInvalidInputLength() {
        listCommand = new ListCommand("listtaskondate 2022-01-01", 1);

        listCommand.listTasksOnDate("listtaskondate", mockTaskList, mockUi); // Short command

        // Verify that an error message is printed
        verify(mockUi).printMessage(contains("Oopses daisy!"));
    }

    @Test
    void testListTasksOnDateNoTasks() {
        String command = "listtaskondate 2022-01-01";
        listCommand = new ListCommand(command, 1);
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>());

        listCommand.listTasksOnDate(command, mockTaskList, mockUi);

        // Verify message for no tasks on the date is printed
        verify(mockUi).printMessage(contains("no tasks on 2022-01-01"));
    }

    @Test
    void testListTasksOnDateInvalidDateFormat() {
        String command = "listtaskondate invalid-date";
        listCommand = new ListCommand(command, 1);

        listCommand.listTasksOnDate(command, mockTaskList, mockUi);

        // Verify that an error message for invalid date format is printed
        verify(mockUi).printMessage(contains("invalid date time format"));
    }

}
