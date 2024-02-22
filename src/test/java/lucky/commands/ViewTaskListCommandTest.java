package lucky.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lucky.tasks.Task;

public class ViewTaskListCommandTest {
    @Test
    public void execute_validInput_success() throws IOException, CommandException {
        // Arrange
        ViewTaskListCommand viewTaskListCommand = new ViewTaskListCommand();
        ArrayList<Task> tasks = new ArrayList<>();

        String[] input = { "list" };

        assertDoesNotThrow(() -> viewTaskListCommand.execute(tasks, input));
    }
}
