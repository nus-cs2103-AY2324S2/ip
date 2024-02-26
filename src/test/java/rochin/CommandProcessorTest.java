package rochin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
    @Test
    public void testProcessDeadlineCommand_validInput() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        String command = "deadline Finish assignment /by 2024-02-29 1800";
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processDeadlineCommand(command, tasks, ui);

        assertEquals(1, tasks.getAllTasks().size());
        assertEquals("[D][ ] Finish assignment (by: Feb 29 2024 1800)", tasks.getAllTasks().get(0).toString());
    }

    @Test
    public void testProcessEventCommand_validInput() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        String command = "event Finish assignment /from 2024-02-29 1800 /to 2024-02-29 1900";
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processEventCommand(command, tasks, ui);

        assertEquals(1, tasks.getAllTasks().size());
        assertEquals("[E][ ] Finish assignment (from: Feb 29 2024 1800 to: Feb 29 2024 1900)", tasks.getAllTasks().get(0).toString());
    }
}
