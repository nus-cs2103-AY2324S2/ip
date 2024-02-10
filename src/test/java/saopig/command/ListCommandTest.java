package saopig.command;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

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
        String result;
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>());
        listCommand = new ListCommand("", 0);
        result = listCommand.listTasks(mockTaskList, mockUi);
        assert result.contains("no tasks yet");
    }

    @Test
    void testListTasksWithNonEmptyTaskList() {
        String result;
        Task mockTask = mock(Task.class);
        when(mockTask.toString()).thenReturn("Task Details");
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>(Arrays.asList(mockTask, mockTask)));
        listCommand = new ListCommand("", 0);
        result = listCommand.listTasks(mockTaskList, mockUi);
        assert result.contains("Task Details");
    }

    @Test
    void testListTasksOnDateInvalidInputLength() {
        String result;
        listCommand = new ListCommand("listtaskondate 2022-01-01", 1);
        result = listCommand.listTasksOnDate("listtaskondate", mockTaskList, mockUi);
        assert result.contains("Oopses daisy!");
    }

    @Test
    void testListTasksOnDateNoTasks() {
        String command = "listtaskondate 2022-01-01";
        String result;
        listCommand = new ListCommand(command, 1);
        when(mockTaskList.getTasks()).thenReturn(new ArrayList<>());
        result = listCommand.listTasksOnDate(command, mockTaskList, mockUi);
        assert result.contains("no tasks on 2022-01-01");
    }

    @Test
    void testListTasksOnDateInvalidDateFormat() {
        String command = "listtaskondate invalid-date";
        String result;
        listCommand = new ListCommand(command, 1);
        result = listCommand.listTasksOnDate(command, mockTaskList, mockUi);
        assert result.contains("invalid date time format");
    }

}
