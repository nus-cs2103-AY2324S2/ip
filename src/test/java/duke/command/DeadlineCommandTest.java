package duke.command;

import org.junit.jupiter.api.Test;
import duke.TaskList;
import duke.Storage;
import duke.TextUi;
import duke.task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineCommandTest {

    @Test
    void executeTest() {
        DeadlineCommand deadline = new DeadlineCommand("tutorial /2023-01-01");
        TaskList tasksList = new TaskList(new ArrayList<Task>());
        TextUi ui = new TextUi();
        Storage storage = new Storage(System.getProperty("user.dir") + "/testoutput.txt");
        deadline.execute(tasksList, ui, storage);
        assertEquals("[ ] Deadline : tutorial [ Jan 01 2023 ]", tasksList.getList().get(0).toString());
    }

    @Test
    void isExitTest() {
    }
}