package task;
import command.AddCommand;
import command.Command;
import roland.Storage;
import roland.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import roland.Ui;

import java.util.ArrayList;

public class AddCommandTest {

    @Test
    public void executeTest() {
        Task task = new ToDos("assignment");
        TaskList taskList = new TaskList();
        Command addToDo = new AddCommand(task);
        Ui ui = new Ui();
        Storage storage = new Storage("./src/main/java/data/roland.txt");
        addToDo.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("assignment", taskList.get(0).getDescription());
    }
}
