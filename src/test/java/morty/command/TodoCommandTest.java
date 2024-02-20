package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.MortyException;
import morty.Response;
import morty.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for TodoCommand.
 */
public class TodoCommandTest {

    private TaskList tasks;
    private Response ui;
    private Storage storage;
    private String[] tokens;

    @BeforeEach
    public void setUp() {
        // Initialize your mocks
        tasks = Mockito.mock(TaskList.class);
        ui = Mockito.mock(Response.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    public void execute_todoCommand_addsNewTodo() throws MortyException {
        // Set up tokens to simulate user input for a todo task
        tokens = new String[] { "todo", "new todo" };
        TodoCommand todoCommand = new TodoCommand(tokens);

        // Execute the command
        todoCommand.execute(tasks, ui, storage);

        // Verify a Todo object was added to the task list
        verify(tasks, times(1)).add(any());

        // Verify UI method to show added task was called
        // Since we cannot verify the size without actual implementation,
        // we focus on ensuring this method is called, which implies addition.
        verify(ui, times(1)).showTaskAdded(any(), anyInt());

        // Verify storage save method was called, implying the task was persisted
        verify(storage, times(1)).save(tasks);
    }

    @Test
    public void execute_todoCommandWithMissingTitle_showsUsage() throws MortyException {
        // Set up tokens to simulate user input with missing task title
        tokens = new String[] { "todo" };
        TodoCommand todoCommand = new TodoCommand(tokens);

        // Execute the command
        todoCommand.execute(tasks, ui, storage);

        // Verify UI method to show usage message was called
        verify(ui, times(1)).showTodoUsage();

        // Verify that no task is added and no attempt to save to storage is made
        verify(tasks, never()).add(any(Todo.class));
        verify(storage, never()).save(any(TaskList.class));
    }
}
