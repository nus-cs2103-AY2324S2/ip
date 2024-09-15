package raphael.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import raphael.RaphaelTest;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_validTaskIndex() {
        try {
            raphael.task.TaskList tasks = new raphael.task.TaskList();
            tasks.addTask(new raphael.task.Task("stub", true));
            new DeleteCommand(0).execute(tasks, new raphael.ui.Ui(),
                    new raphael.storage.Storage(RaphaelTest.FILE_PATH));
        } catch (raphael.exception.RaphaelException e) {
            fail();
        }
    }
    @Test
    public void deleteCommand_invalidTaskIndex_exceptionThrown() {
        try {
            new DeleteCommand(0).execute(new raphael.task.TaskList(), new raphael.ui.Ui(),
                    new raphael.storage.Storage(RaphaelTest.FILE_PATH));
            fail();
        } catch (raphael.exception.RaphaelException e) {
            assertEquals("Invalid task index!", e.getMessage());
        }
    }
}
