package duke.commands;

import org.junit.jupiter.api.Test;
import duke.tasks.Task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommandTest {

    @Test
    public void execute_validInput_success() throws IOException, CommandException {
        DeleteCommand deleteCommand = new DeleteCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Sample task"));
        String[] input = {"delete", "1"};

        assertDoesNotThrow(() -> deleteCommand.execute(tasks, input));
        assertEquals(0, tasks.size());
    }

    @Test
    public void execute_invalidInput_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"delete"};

        assertThrows(CommandException.class, () -> deleteCommand.execute(tasks, input));
    }

    @Test
    public void execute_nonExistentTaskIndex_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"delete", "1"};

        assertThrows(CommandException.class, () -> deleteCommand.execute(tasks, input));
    }
}
