package duke.commands;

import org.junit.jupiter.api.Test;
import duke.tasks.Task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.ArrayList;

public class CommandTest {

    @Test
    public void execute_defaultBehavior_outputMessage() {
        Command command = new Command();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"unknownCommand"};

        assertDoesNotThrow(() -> command.execute(tasks, input));
    }
}
