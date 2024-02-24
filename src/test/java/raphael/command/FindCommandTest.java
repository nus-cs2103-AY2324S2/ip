package raphael.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import raphael.RaphaelTest;
import raphael.exception.RaphaelException;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

public class FindCommandTest {
    @Test
    public void findCommandTest_emptyList() {
        try {
            new FindCommand("stub").execute(new TaskList(), new Ui(), new Storage(RaphaelTest.FILE_PATH));
        } catch (RaphaelException e) {
            fail();
        }
    }
}
