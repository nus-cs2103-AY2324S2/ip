package irwyn.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.MarkCommand;
import irwyn.tasks.TaskList;
import irwyn.tasks.ToDo;
import misc.StorageManager;
import misc.Ui;

public class MarkCommandTest {
    protected StorageManager s;
    protected TaskList t;
    protected Ui ui = new Ui();
    @Test
    public void testMark() throws Exception {
        String filePath = System.getProperty("user.dir") + "/storage/testTaskData.txt";
        s = new StorageManager(filePath);
        t = new TaskList(s.load());
        ToDo todo = new ToDo("test");
        t.addTask(todo);
        MarkCommand markCommand = new MarkCommand("mark 2");
        markCommand.execute(t, ui, s);

        assertEquals("X", t.getTask(1).getStatusIcon());
    }
}
