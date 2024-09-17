package lucky.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lucky.tasks.Task;

public class CommandTest {

    @Test
    public void execute_defaultBehavior_outputMessage() {
        Command command = new Command();
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = {"unknownCommand"};

        assertDoesNotThrow(() -> command.execute(tasks, input));
    }
}
