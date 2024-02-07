package duke;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;
import duke.commands.DeadlineCommand;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineCommandTest {

    @Test
    public void execute_validInput_success() {
        DeadlineCommand deadlineCommand = new DeadlineCommand("Finish homework /by 07/02/2024 1800");
        TaskList tasks = new TaskList();
        try {
            List<String> messages = deadlineCommand.execute(tasks);
            assertEquals(3, messages.size());
            assertEquals("Got it. I've added this task:", messages.get(0));
            assertEquals("added: [D][ ] Finish homework (by: 07 Feb 2024 06:00 PM)", messages.get(1));
            assertEquals("Now you have 1 tasks in the list.", messages.get(2));
            assertEquals(1, tasks.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void execute_invalidFormat_exceptionThrown() {
        DeadlineCommand deadlineCommand = new DeadlineCommand("Finish homework by 07/02/2024 1800");
        TaskList tasks = new TaskList();
        assertThrows(DukeException.class, () -> deadlineCommand.execute(tasks));
    }

    @Test
    public void execute_invalidDateTimeFormat_exceptionThrown() {
        DeadlineCommand deadlineCommand = new DeadlineCommand("Finish homework /by 07-02-2024 1800");
        TaskList tasks = new TaskList();
        assertThrows(DukeException.class, () -> deadlineCommand.execute(tasks));
    }

}
