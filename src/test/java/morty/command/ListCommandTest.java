package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * Test for ListCommand.
 */
public class ListCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ListCommand listCommand;

    @BeforeEach
    public void setUp() {
        // Initialize your mocks
        tasks = Mockito.mock(TaskList.class);
        ui = Mockito.mock(Ui.class);
        storage = Mockito.mock(Storage.class);

        // Assume the tokens parameter is not used in execute method for simplification
        String[] tokens = new String[]{};
        listCommand = new ListCommand(tokens);
    }

    @Test
    public void execute_listCommand_success() {
        // Execute the list command
        listCommand.execute(tasks, ui, storage);

        // Verify that ui.showTaskList(tasks) was called exactly once
        verify(ui, times(1)).showTaskList(tasks);

        // Since ListCommand doesn't interact with storage or tasks in this implementation,
        // there's no need to verify interactions with those mocks in this test case.
    }
}
