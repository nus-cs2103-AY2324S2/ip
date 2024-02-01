package duke.commands;

import org.junit.jupiter.api.Test;
import duke.tasks.Task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.IOException;
import java.util.ArrayList;

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
