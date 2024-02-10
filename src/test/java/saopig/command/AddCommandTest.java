package saopig.command;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.TaskList;
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
    public void testAddDeadlineTask() {
        addCommand.addDeadlineTask("deadline return book /by 2020-02-02 18:00",
                mockTaskList, mockUi, mockStorage);

        // Verify if a Deadline task was added to the task list
        verify(mockTaskList).addDeadlineTask(any(Deadline.class));

        // Verify if the task list was saved
        verify(mockStorage).saveTaskList(any(TaskList.class));
    }

    @Test
    public void testAddEventTask() {
        addCommand.addEventTask("event project meeting2 /from 2025-01-02 12:00 /to 2025-03-02 12:00",
                mockTaskList, mockUi, mockStorage);

        // Verify if an Event task was added to the task list
        verify(mockTaskList).addEventTask(any(Event.class));

        // Verify if the task list was saved
        verify(mockStorage).saveTaskList(any(TaskList.class));
    }

    // Additional tests can be written to cover edge cases and exceptions
}
