package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import seedu.duke.command.TodoCommand;
import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Todo;
import seedu.duke.ui.Ui;





public class TodoTest {
    @Test
    public void testToDo() throws DuplicateTaskException {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        String description = "hello";
        new TodoCommand(description).execute(mockTaskList, mockUi, mockStorage);

        verify(mockTaskList).addTask(argThat(task -> {
            assertTrue(task instanceof Todo);
            assertEquals(task.getDescription(), description);
            return true;
        }));

        verify(mockUi).generateNewTaskResponse(argThat(task -> {
            assertTrue(task instanceof Todo);
            assertEquals(task.getDescription(), description);
            return true;
        }), eq(mockTaskList));
    }
}
