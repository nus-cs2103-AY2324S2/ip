package lucky.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lucky.tasks.Task;

public class AddEventCommandTest {
    @Test
    public void execute_validInput_success() {
        AddEventCommand addEventCommand = new AddEventCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"event", "Attend meeting /from 02/02/2024 0900 /to 02/02/2024 1000"};

        assertDoesNotThrow(() -> addEventCommand.execute(tasks, input));
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_invalidInput_throwsCommandException() {
        AddEventCommand addEventCommand = new AddEventCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"event", "Invalid input"};

        assertThrows(CommandException.class, () -> addEventCommand.execute(tasks, input));
    }

    @Test
    public void execute_invalidDateTimeFormat_throwsCommandException() {
        AddEventCommand addEventCommand = new AddEventCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"event", "Attend party /from 31/31/2024 /to 31/31/2024"};

        assertThrows(CommandException.class, () -> addEventCommand.execute(tasks, input));
    }
}
