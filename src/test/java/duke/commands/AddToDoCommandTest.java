package duke.commands;

import org.junit.jupiter.api.Test;
import duke.tasks.Task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

public class AddToDoCommandTest {
    
    @Test
    public void execute_validInput_success() {
        AddToDoCommand addToDoCommand = new AddToDoCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"todo", "finish JUnit testing );"};

        assertDoesNotThrow(() -> addToDoCommand.execute(tasks, input));
        assertEquals(1, tasks.size());
    }

    @Test
    public void execute_invalidInput_throwsCommandException() {
        AddToDoCommand addToDoCommand = new AddToDoCommand();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"todo"};

        assertThrows(CommandException.class, () -> addToDoCommand.execute(tasks, input));
    }
}
