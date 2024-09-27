package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import core.Ui;
import data.Storage;

public class TaskListTest {
    private TaskList taskList;
    private Ui mockUi;
    private Storage mockStorage;
    private final String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        List<Task> loadedTasks = new ArrayList<>();
        mockUi = mock(Ui.class);
        mockStorage = mock(Storage.class);
        taskList = new TaskList(loadedTasks);
    }

    @Test
    public void addTask_addToDo_toDoAdded() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String input = "TOdo sample task";
        taskList.addTask(input, mock(Ui.class), mock(Storage.class));
        Task addedTask = taskList.getTasks().get(0);
        assertTrue(addedTask instanceof ToDo, "The added task should be an instance of ToDo");
    }

    @Test
    public void addTask_addDeadline_deadlineAdded() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String input = "deadline return book /by 2022-12-30";
        taskList.addTask(input, mock(Ui.class), mock(Storage.class));
        Task addedTask = taskList.getTasks().get(0);
        assertTrue(addedTask instanceof Deadline, "The added task should be an instance of Deadline");
    }

    @Test
    public void addTask_addEvent_eventAdded() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String input = "event project meeting /from 2022-12-15 /to 2022-12-16";
        taskList.addTask(input, mock(Ui.class), mock(Storage.class));
        Task addedTask = taskList.getTasks().get(0);
        assertTrue(addedTask instanceof Event, "The added task should be an instance of Event");
    }

    @Test
    public void deleteTask_deleteTask_taskDeleted() {
        String inputAddFirst = "todo sample task";
        String inputAddSecond = "Deadline return book /by 2022-12-30";
        taskList.addTask(inputAddFirst, mockUi, mockStorage);
        taskList.addTask(inputAddSecond, mockUi, mockStorage);
        assertEquals(2, taskList.getTasks().size(), "Setup failed to add task.");

        taskList.deleteTask("delete 2", mockUi, mockStorage);
        Task remainedTask = taskList.getTasks().get(0);
        assertTrue(remainedTask instanceof ToDo, "The remained task should be an instance of Deadline");
    }

    @Test
    public void deleteTask_wrongFormat_messagePrinted() {
        taskList.addTask("deadline return book /by 2022-12-30", mockUi, mockStorage);
        taskList.addTask("event project meeting /from 2022-12-30 /to 2022-12-31", mockUi, mockStorage);

        String inputWrongFormat = "delete deadline";

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        taskList.deleteTask(inputWrongFormat, mockUi, mockStorage);

        verify(mockUi, atLeastOnce()).showFormatError(messageCaptor.capture());
        String capturedMessages = String.join(lineSeparator, messageCaptor.getAllValues());

        assertTrue(capturedMessages.contains("delete [task index]"), "Should show error message.");
    }


    /*
    @Test
    public void listTasksOnDate_twoTasks_tasksPrinted() {
        taskList.addTask("deadline return book /by 2022-12-30", mockUi, mockStorage);
        taskList.addTask("event project meeting /from 2022-12-30 /to 2022-12-31", mockUi, mockStorage);

        reset(mockUi);

        taskList.listTasksOnDate("list_on 2022-12-30", mockUi);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockUi, atLeastOnce()).showMessage(messageCaptor.capture());

        List<String> capturedMessages = messageCaptor.getAllValues();
        String combinedMessages = String.join(System.lineSeparator(), capturedMessages);

        assertTrue(combinedMessages.contains("return book"));
        assertTrue(combinedMessages.contains("project meeting"), "Should list tasks on the specified date.");
    }


    @Test
    public void ListTasksOnDate_noTask_messagePrinted() {
        taskList.addTask("deadline return book /by 2022-12-30", mockUi, mockStorage);
        taskList.addTask("event project meeting /from 2022-12-30 /to 2022-12-31", mockUi, mockStorage);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        taskList.listTasksOnDate("list_on 2022-12-29", mockUi);

        verify(mockUi, atLeastOnce()).showMessage(messageCaptor.capture());
        String capturedMessages = String.join(LINE_SEPARATOR, messageCaptor.getAllValues());

        assertTrue(capturedMessages.contains("no task"), "Should have no task to list on the specified date.");
    }
     */

    @Test
    public void testMarkTask() {
        taskList.addTask("todo sample task", mockUi, mockStorage);
        taskList.markTask("mark 1", mockUi, mockStorage);
        assertTrue(taskList.getTasks().get(0).isDone(), "Should mark the specified task as done.");
    }

    @Test
    public void testUnmarkTask() {
        taskList.addTask("todo sample task", mockUi, mockStorage);
        taskList.markTask("mark 1", mockUi, mockStorage);
        taskList.unmarkTask("unmark 1", mockUi, mockStorage);
        assertFalse(taskList.getTasks().get(0).isDone(), "Should unmark the specified task as not done.");
    }
}
