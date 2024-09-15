package raphael.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import raphael.RaphaelTest;
import raphael.storage.Storage;
import raphael.task.Task;
import raphael.task.TaskList;
import raphael.ui.Ui;

public class AddCommandTest {
    @Test
    public void addCommandTest_doneTask_success() {
        try {
            Task toAdd = new Task("stub", true);
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage(RaphaelTest.FILE_PATH);
            new AddCommand(toAdd).execute(tasks, ui, storage);
            String expectedOutput = String.format("Roger that! I have added the following task into your list:\n"
                    + "\t%s\n%s", toAdd, tasks.getSize());
            assertEquals(expectedOutput, ui.getOutput());
        } catch (raphael.exception.RaphaelException e) {
            fail();
        }
    }
    @Test
    public void addCommandTest_undoneTask_success() {
        try {
            Task toAdd = new Task("stub", true);
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage(RaphaelTest.FILE_PATH);
            new AddCommand(toAdd).execute(tasks, ui, storage);
            String expectedOutput = String.format("Roger that! I have added the following task into your list:\n"
                    + "\t%s\n%s", toAdd, tasks.getSize());
            assertEquals(expectedOutput, ui.getOutput());
        } catch (raphael.exception.RaphaelException e) {
            fail();
        }
    }
}
