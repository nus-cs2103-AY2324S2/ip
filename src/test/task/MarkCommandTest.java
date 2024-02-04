package task;

import command.AddCommand;
import command.Command;
import command.MarkCommand;
import org.junit.jupiter.api.Test;
import roland.Storage;
import roland.TaskList;
import roland.Ui;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MarkCommandTest {

    @Test
    public void executeTest() {
        Task task = new ToDos("assignment");
        TaskList taskList = new TaskList();
        Command addToDo = new AddCommand(task);
        Ui ui = new Ui();
        Storage storage = new Storage("./src/main/java/data/roland.txt");
        addToDo.execute(taskList, ui, storage);

        Command markDone = new MarkCommand(0, true);
        markDone.execute(taskList, ui, storage);
        assertEquals(true, taskList.get(0).getIsDone());

        Command markUndone = new MarkCommand(0, false);

        assertEquals(false, taskList.get(0).getIsDone());
    }
}
