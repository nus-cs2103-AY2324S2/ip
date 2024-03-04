package irwyn.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.DeadlineCommand;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;


public class DeadlineCommandTest {
    protected StorageManager s;
    protected TaskList t;
    protected Ui ui = new Ui();

    @Test
    public void testAddDeadline() throws Exception {
        String filePath = System.getProperty("user.dir") + "/storage/testTaskData.txt";
        s = new StorageManager(filePath);
        t = new TaskList(s.load());
        DeadlineCommand deadline = new DeadlineCommand("deadline test /by 2024-02-04 18:00:00");
        deadline.execute(t, ui, s);
        String expectedOutput = "[D][ ] test (by: Feb 04 2024 18:00)";
        String actualOutput = t.getTasks().get(0).toString();
        assertEquals(expectedOutput, actualOutput);
    }
}
