package duke.commands;
import org.junit.jupiter.api.Test;
import duke.tasks.Task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;



public class AddDeadlineCommandTest {

    @Test
    public void execute_validInput_success() {
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"deadline", "Finish project /by 31/12/2024 2359"};

        assertDoesNotThrow(() -> addDeadlineCommand.execute(tasks, input));
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_invalidInput_throwsCommandException() {
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"deadline", "Invalid input"};

        assertThrows(CommandException.class, () -> addDeadlineCommand.execute(tasks, input));
    }

    @Test
    public void execute_invalidDateTimeFormat_throwsCommandException() {
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"deadline", "Finish project /by 31/31/2024"};

        assertThrows(CommandException.class, () -> addDeadlineCommand.execute(tasks, input));
    }
}
