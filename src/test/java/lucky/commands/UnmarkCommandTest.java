package lucky.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lucky.tasks.Task;

public class UnmarkCommandTest {
    @Test
    public void execute_validInput_success() throws IOException, CommandException {
        UnmarkCommand unmarkCommand = new UnmarkCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Task("Sample task");
        task.setMarked(true);
        tasks.add(task);

        String[] input = {"unmark", "1"};

        assertDoesNotThrow(() -> unmarkCommand.execute(tasks, input));
        assertFalse(task.getIsMarked());
    }

    @Test
    public void execute_invalidInput_throwsCommandException() {
        UnmarkCommand unmarkCommand = new UnmarkCommand();
        ArrayList<Task> tasks = new ArrayList<>();

        String[] input = {"unmark"};

        assertThrows(CommandException.class, () -> unmarkCommand.execute(tasks, input));
    }

    @Test
    public void execute_nonExistentTaskIndex_throwsCommandException() {
        UnmarkCommand unmarkCommand = new UnmarkCommand();
        ArrayList<Task> tasks = new ArrayList<>();

        String[] input = {"unmark", "1"};

        assertThrows(CommandException.class, () -> unmarkCommand.execute(tasks, input));
    }

    @Test
    public void execute_alreadyUnmarkedTask_throwsCommandException() {
        UnmarkCommand unmarkCommand = new UnmarkCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Task("Sample task");
        tasks.add(task);

        String[] input = {"unmark", "1"};

        assertThrows(CommandException.class, () -> unmarkCommand.execute(tasks, input));
    }
}
