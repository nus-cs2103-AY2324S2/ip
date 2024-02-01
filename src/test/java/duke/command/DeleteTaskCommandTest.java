package duke.command;

import duke.task.ToDo;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteTaskCommandTest {
    @Test
    public void execute_invalidTaskIndex_exceptionThrown() throws IOException {
        try {
            Command testCommand = new DeleteTaskCommand(1);
            testCommand.execute(new TaskList(), new Ui(), new Storage("./data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals("*HONK* Pengu thinks you need a valid task number to delete, " +
                    "consider checking the list command", e.getMessage());
        }
    }

    @Test
    public void execute_validTaskIndex_success() throws DukeException, IOException{
        TaskList testList = new TaskList();
        testList.addTask(new ToDo("task index 0"));
        Command testCommand = new DeleteTaskCommand(0);
        testCommand.execute(testList, new Ui(), new Storage("./data/tasks.txt"));
        assertEquals(0, testList.listSize());
    }
}