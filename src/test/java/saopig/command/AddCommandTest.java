package saopig.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.TaskList;
import saopig.task.Todo;

import static org.mockito.Mockito.*;

class AddCommandTest {

    private TaskList mockTaskList;
    private Ui mockUi;
    private Storage mockStorage;
    private AddCommand addCommand;

    @BeforeEach
    public void setUp() {
        mockTaskList = mock(TaskList.class);
        mockUi = mock(Ui.class);
        mockStorage = mock(Storage.class);
        addCommand = new AddCommand("todo read book", 0);
    }

    @Test
    public void testAddTodoTask() {
        addCommand.addTodoTask("todo read book", mockTaskList, mockUi, mockStorage);

        // Verify if a Todo task was added to the task list
        verify(mockTaskList).addTodoTask(any(Todo.class));

        // Verify if the task list was saved
        verify(mockStorage).saveTaskList(any(TaskList.class));

        // Verify if a success message was shown
        verify(mockUi, atLeastOnce()).printMessage(contains("has been added successfully"));
    }

    @Test
    public void testAddDeadlineTask() {
        addCommand.addDeadlineTask("deadline return book /by 2020-02-02 18:00", mockTaskList, mockUi, mockStorage);

        // Verify if a Deadline task was added to the task list
        verify(mockTaskList).addDeadlineTask(any(Deadline.class));

        // Verify if the task list was saved
        verify(mockStorage).saveTaskList(any(TaskList.class));

        // Verify if a success message was shown
        verify(mockUi, atLeastOnce()).printMessage(contains("has been added successfully"));
    }

    @Test
    public void testAddEventTask() {
        addCommand.addEventTask("event project meeting2 /from 2025-01-02 12:00 /to 2025-03-02 12:00", mockTaskList, mockUi, mockStorage);

        // Verify if an Event task was added to the task list
        verify(mockTaskList).addEventTask(any(Event.class));

        // Verify if the task list was saved
        verify(mockStorage).saveTaskList(any(TaskList.class));

        // Verify if a success message was shown
        verify(mockUi, atLeastOnce()).printMessage(contains("has been added successfully"));
    }

    // Additional tests can be written to cover edge cases and exceptions
}
