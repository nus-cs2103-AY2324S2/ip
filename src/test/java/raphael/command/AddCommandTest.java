package raphael.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
public class AddCommandTest {
    @Test
    public void addCommandTest1() {
        try {
            new AddCommand(new raphael.task.Task("stub", true)).execute(
                    new raphael.task.TaskList(), new raphael.ui.Ui(),
                    new raphael.storage.Storage("./data/tasks.txt")
            );
        } catch (raphael.exception.RaphaelException e) {
            fail();
        }
    }
    @Test
    public void addCommandTest2() {
        try {
            new AddCommand(new raphael.task.Task("stub", false)).execute(
                    new raphael.task.TaskList(), new raphael.ui.Ui(),
                    new raphael.storage.Storage("./data/tasks.txt")
            );
        } catch (raphael.exception.RaphaelException e) {
            fail();
        }
    }
}
